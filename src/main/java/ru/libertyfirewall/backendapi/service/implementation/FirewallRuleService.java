package ru.libertyfirewall.backendapi.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.libertyfirewall.backendapi.enumeration.modules.FilesNames;
import ru.libertyfirewall.backendapi.enumeration.modules.ModulesNames;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.model.output.Module;
import ru.libertyfirewall.backendapi.model.output.Modules;
import ru.libertyfirewall.backendapi.model.rules.FirewallRule;
import ru.libertyfirewall.backendapi.redis.RedisRulesPublisher;
import ru.libertyfirewall.backendapi.repository.GroupRepository;
import ru.libertyfirewall.backendapi.repository.RuleRepository;
import ru.libertyfirewall.backendapi.service.RuleService;
import ru.libertyfirewall.backendapi.util.output.OutputMessage;
import ru.libertyfirewall.backendapi.util.rules.FirewallRuleCreator;
import ru.libertyfirewall.backendapi.util.rules.RulesStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FirewallRuleService implements RuleService<FirewallRule> {
    /**
     * Управление правилами файервола сурикаты.
     */
    private final RuleRepository ruleRepository;

    private final GroupRepository groupRepository;
    private final RedisRulesPublisher rulesPublisher;
    private final FirewallRuleCreator firewallRuleCreator;

    @Override
    public FirewallRule create(FirewallRule firewallRule) throws ValidationException {
        log.info("Saving new rule");
        if (!isValidRule(firewallRule))
            throw new ValidationException("Неверно указаны ip-адреса или ID групп.");
        if (firewallRule.getSrcGroup() != null) {
            Long srcGroupId = firewallRule.getSrcGroup().getId();
            Long dstGroupId = firewallRule.getDstGroup().getId();
            GroupContainer srcGroup = groupRepository.findById(srcGroupId).get();
            GroupContainer dstGroup = groupRepository.findById(dstGroupId).get();
            firewallRule.setSrcGroup(srcGroup);
            firewallRule.setDstGroup(dstGroup);
        }
        FirewallRule firewallRuleSaved = ruleRepository.saveAndFlush(firewallRule);
        // получаем все текущие правила
        List<FirewallRule> relevantRulesList = ruleRepository.findAll();
        // парсинг всех правил
        RulesStorage rulesStorage = firewallRuleCreator.createRules(relevantRulesList);
        //формируем модель выходных данных
        Module firewallRuleModule = Module.builder()
                .name(ModulesNames.FIREWALL)
                .fileName(FilesNames.FIREWALL.getFilename())
                .ruleset(rulesStorage.getRulesStorage())
                .build();
        Modules firewallModules = new Modules(List.of(firewallRuleModule));
        String outputMessage = OutputMessage.createMessage(firewallModules);
        // отправление в редис
        rulesPublisher.publish(outputMessage);
        return firewallRuleSaved;
    }

    @Override
    public boolean delete(Long id) throws NoSuchRuleException {
        log.info("Deleting rule with id: {}", id);
        if (ruleRepository.findById(id).isEmpty()) {
            throw new NoSuchRuleException("Нет правила с таким ID");
        }
        ruleRepository.deleteById(id);
        return true;
    }

    @Override
    public List<FirewallRule> list() {
        log.info("Fetching list of rules");
        return ruleRepository.findAll();
    }

    public boolean isValidRule(FirewallRule firewallRule) {
        if (firewallRule.getSrcIP() == null && firewallRule.getSrcGroup() == null
                || firewallRule.getDstIP() == null && firewallRule.getDstGroup() == null)
            return false;
        return true;
    }
}

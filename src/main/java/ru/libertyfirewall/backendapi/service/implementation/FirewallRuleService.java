package ru.libertyfirewall.backendapi.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.FirewallRule;
import ru.libertyfirewall.backendapi.redis.RedisRulesPublisher;
import ru.libertyfirewall.backendapi.repository.RuleRepository;
import ru.libertyfirewall.backendapi.service.RuleService;
import ru.libertyfirewall.backendapi.util.FirewallRuleCreator;
import ru.libertyfirewall.backendapi.util.RulesStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FirewallRuleService implements RuleService<FirewallRule> {
    /**
     * Управление правилами сурикаты.
     */
    private final RuleRepository ruleRepository;
    private final RedisRulesPublisher rulesPublisher;

    private final FirewallRuleCreator firewallRuleCreator;

    @Override
    public FirewallRule create(FirewallRule firewallRule) throws ValidationException {
        log.info("Saving new rule");
        if (!isValidRule(firewallRule))
            throw new ValidationException("Неверно указаны ip-адреса или ID групп.");
        FirewallRule firewallRuleSaved = ruleRepository.save(firewallRule);
        // парсинг правила
        RulesStorage rulesStorage = firewallRuleCreator.createRules(firewallRuleSaved);
        // отправление в редис
        for (String parsedRule: rulesStorage.getRulesStorage()) {
            rulesPublisher.publish(parsedRule);
            log.info("Rules publisher topic {}", rulesPublisher);
        }
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
        if (firewallRule.getSrcIPs() == null && firewallRule.getSrcGroupID() == null
                || firewallRule.getDstIPs() == null && firewallRule.getDstGroupID() == null)
            return false;
        return true;
    }
}

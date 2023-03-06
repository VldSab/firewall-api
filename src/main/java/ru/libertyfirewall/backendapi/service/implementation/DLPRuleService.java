package ru.libertyfirewall.backendapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.libertyfirewall.backendapi.enumeration.modules.FilesNames;
import ru.libertyfirewall.backendapi.enumeration.modules.ModulesNames;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.model.output.Module;
import ru.libertyfirewall.backendapi.model.output.Modules;
import ru.libertyfirewall.backendapi.model.rules.DLPRule;
import ru.libertyfirewall.backendapi.redis.RedisRulesPublisher;
import ru.libertyfirewall.backendapi.repository.DLPRuleRepository;
import ru.libertyfirewall.backendapi.repository.GroupRepository;
import ru.libertyfirewall.backendapi.service.RuleService;
import ru.libertyfirewall.backendapi.util.output.OutputMessage;
import ru.libertyfirewall.backendapi.util.rules.DLPRuleCreator;
import ru.libertyfirewall.backendapi.util.rules.RulesStorage;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class DLPRuleService implements RuleService<DLPRule> {

    private final DLPRuleRepository ruleRepository;
    private final GroupRepository groupRepository;
    private final RedisRulesPublisher rulesPublisher;
    private final DLPRuleCreator firewallRuleCreator;

    @Override
    public DLPRule create(DLPRule firewallRule) throws  NoSuchGroupException {
        log.info("Сохранение dlp-правила");
        Long srcGroupId = firewallRule.getSrcGroup().getId();
        Long dstGroupId = firewallRule.getDstGroup().getId();
        Optional<GroupContainer> srsGroup = groupRepository.findById(srcGroupId);
        Optional<GroupContainer> dstGroup = groupRepository.findById(dstGroupId);
        if (srsGroup.isEmpty() || dstGroup.isEmpty())
            throw new NoSuchGroupException("Группы с полученными ID не найдены");
        firewallRule.setSrcGroup(srsGroup.get());
        firewallRule.setDstGroup(dstGroup.get());
        ruleRepository.save(firewallRule);
        // получаем все текущие правила
        List<DLPRule> relevantRulesList = ruleRepository.findAll();
        // парсинг всех правил
        RulesStorage rulesStorage = firewallRuleCreator.createRules(relevantRulesList);
        //формируем модель выходных данных
        Module dlpRuleModule = Module.builder()
                .name(ModulesNames.DLP)
                .fileName(FilesNames.DLP.getFilename())
                .ruleset(rulesStorage.getRulesStorage())
                .build();
        Modules dlpModules = new Modules(List.of(dlpRuleModule));
        String outputMessage = OutputMessage.createMessage(dlpModules);
        // отправление в редис
        rulesPublisher.publish(outputMessage);
        return firewallRule;
    }

    @Override
    public boolean delete(Long id) throws NoSuchRuleException {
        log.info("Удаление dlp-правила");
        Optional<DLPRule> dlpRule = ruleRepository.findById(id);
        if (dlpRule.isEmpty())
            throw new NoSuchRuleException("Нет DLP-правила с таким ID");
        ruleRepository.deleteById(id);
        // получаем все текущие правила
        List<DLPRule> relevantRulesList = ruleRepository.findAll();
        // парсинг всех правил
        RulesStorage rulesStorage = firewallRuleCreator.createRules(relevantRulesList);
        //формируем модель выходных данных
        Module dlpRuleModule = Module.builder()
                .name(ModulesNames.DLP)
                .fileName(FilesNames.DLP.getFilename())
                .ruleset(rulesStorage.getRulesStorage())
                .build();
        Modules dlpModules = new Modules(List.of(dlpRuleModule));
        String outputMessage = OutputMessage.createMessage(dlpModules);
        // отправление в редис
        rulesPublisher.publish(outputMessage);
        return true;
    }

    @Override
    public List<DLPRule> list() {
        return null;
    }
}

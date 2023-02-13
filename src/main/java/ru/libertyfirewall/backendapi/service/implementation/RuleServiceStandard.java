package ru.libertyfirewall.backendapi.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.redis.RedisRulesPublisher;
import ru.libertyfirewall.backendapi.repository.RuleRepository;
import ru.libertyfirewall.backendapi.service.RuleService;
import ru.libertyfirewall.backendapi.util.RuleCreator;
import ru.libertyfirewall.backendapi.util.RulesStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RuleServiceStandard implements RuleService {
    /**
     * Управление правилами сурикаты.
     */
    private final RuleRepository ruleRepository;
    private final RedisRulesPublisher rulesPublisher;

    private final RuleCreator ruleCreator;

    @Override
    public Rule create(Rule rule) throws ValidationException {
        log.info("Saving new rule");
        if (!isValidRule(rule))
            throw new ValidationException("Неверно указаны ip-адреса или ID групп.");
        Rule ruleSaved = ruleRepository.save(rule);
        // парсинг правила
        RulesStorage rulesStorage = ruleCreator.createRules(ruleSaved);
        // отправление в редис
        for (String parsedRule: rulesStorage.getRulesStorage()) {
            rulesPublisher.publish(parsedRule);
            log.info("Rules publisher topic {}", rulesPublisher);
        }
        return ruleSaved;
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
    public List<Rule> list() {
        log.info("Fetching list of rules");
        return ruleRepository.findAll();
    }

    public boolean isValidRule(Rule rule) {
        if (rule.getSrcIPs() == null && rule.getSrcGroupID() == null
                || rule.getDstIPs() == null && rule.getDstGroupID() == null)
            return false;
        return true;
    }
}

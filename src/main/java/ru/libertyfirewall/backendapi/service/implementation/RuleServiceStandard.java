package ru.libertyfirewall.backendapi.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleExeption;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.repository.RuleRepository;
import ru.libertyfirewall.backendapi.service.RuleService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RuleServiceStandard implements RuleService {

    private final RuleRepository ruleRepository;

    @Override
    public Rule create(Rule rule) {
        log.info("Saving new rule: {}", rule.getId());
        return ruleRepository.save(rule);
    }

    @Override
    public boolean delete(Long id) throws NoSuchRuleExeption {
        log.info("Deleting rule with id: {}", id);
        if (ruleRepository.findById(id).isEmpty()) {
            throw new NoSuchRuleExeption("No rule with such ID");
        }
        ruleRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Rule> list() {
        log.info("Fetching list of rules");
        return ruleRepository.findAll();
    }
}

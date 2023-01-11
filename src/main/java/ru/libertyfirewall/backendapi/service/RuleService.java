package ru.libertyfirewall.backendapi.service;

import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupExeption;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleExeption;
import ru.libertyfirewall.backendapi.model.Rule;

import java.util.List;

public interface RuleService {
    Rule create(Rule rule);
    boolean delete(Long id) throws NoSuchGroupExeption, NoSuchRuleExeption;
    List<Rule> list();
}

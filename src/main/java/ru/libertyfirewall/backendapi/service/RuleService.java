package ru.libertyfirewall.backendapi.service;

import ru.libertyfirewall.backendapi.model.Rule;

import java.util.List;

public interface RuleService {
    Rule create(Rule rule);
    boolean delete(Long id);
    List<Rule> list();
}

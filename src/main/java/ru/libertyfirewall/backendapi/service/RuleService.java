package ru.libertyfirewall.backendapi.service;

import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.rules.FirewallRule;
import ru.libertyfirewall.backendapi.model.rules.Rule;

import java.util.List;

public interface RuleService<R extends Rule> {
    FirewallRule create(R firewallRule) throws ValidationException;
    boolean delete(Long id) throws NoSuchGroupException, NoSuchRuleException;
    List<FirewallRule> list();
}

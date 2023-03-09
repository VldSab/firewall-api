package ru.libertyfirewall.backendapi.util.rules;

import ru.libertyfirewall.backendapi.model.rules.Rule;

import java.util.List;

public interface RulesCreator<R extends Rule> {
    String parseRule(R rule);
    RulesStorage createRules(R firewallRule);
    RulesStorage createRules(List<R> firewallRules);
}

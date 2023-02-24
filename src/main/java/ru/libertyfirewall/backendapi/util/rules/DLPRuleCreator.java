package ru.libertyfirewall.backendapi.util.rules;

import ru.libertyfirewall.backendapi.model.rules.DLPRule;

import java.util.List;

public class DLPRuleCreator implements RulesCreator<DLPRule> {
    @Override
    public RulesStorage createRules(DLPRule firewallRule) {
        return null;
    }

    @Override
    public RulesStorage createRules(List<DLPRule> firewallRules) {
        return null;
    }
}

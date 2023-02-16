package ru.libertyfirewall.backendapi.util;

import ru.libertyfirewall.backendapi.model.DLPRule;

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

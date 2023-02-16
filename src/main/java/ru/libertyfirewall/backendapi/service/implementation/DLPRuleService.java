package ru.libertyfirewall.backendapi.service.implementation;

import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.model.DLPRule;
import ru.libertyfirewall.backendapi.model.FirewallRule;
import ru.libertyfirewall.backendapi.service.RuleService;

import java.util.List;

public class DLPRuleService implements RuleService<DLPRule> {
    @Override
    public FirewallRule create(DLPRule firewallRule) throws ValidationException {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<FirewallRule> list() {
        return null;
    }
}

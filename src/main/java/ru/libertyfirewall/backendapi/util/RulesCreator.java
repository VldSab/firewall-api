package ru.libertyfirewall.backendapi.util;

import ru.libertyfirewall.backendapi.model.FirewallRule;
import ru.libertyfirewall.backendapi.model.Rule;

import java.util.List;

public interface RulesCreator<R extends Rule> {
    RulesStorage createRules(R firewallRule);
    RulesStorage createRules(List<R> firewallRules);
}

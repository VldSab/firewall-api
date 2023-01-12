package ru.libertyfirewall.backendapi.util.rulecreators;

import ru.libertyfirewall.backendapi.model.Rule;

import java.util.List;

public interface RulesCreator {
    RulesStorage createRules(Rule rule);
    RulesStorage createRules(List<Rule> rules);
}

package ru.libertyfirewall.backendapi.util.rules;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class RulesStorage {
    /**
     * Класс отвечает за хранение правил и их подготовку
     * для последующей отправки в Suricata.
     */
    private final Set<String> rulesStorage = new HashSet<>();

    public RulesStorage(String rule) {
        rulesStorage.add(rule);
    }

    public RulesStorage(Set<String> rulesSet) {
        rulesStorage.addAll(rulesSet);
    }

    public void addRule(String rule) {
        rulesStorage.add(rule);
    }

    public boolean checkRuleExists(String rule) {
        return rulesStorage.contains(rule);
    }
}

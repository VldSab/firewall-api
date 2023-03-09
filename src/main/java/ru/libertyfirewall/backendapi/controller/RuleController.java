package ru.libertyfirewall.backendapi.controller;

import org.springframework.http.ResponseEntity;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.rules.Rule;

public interface RuleController<T extends Rule> {
    ResponseEntity<Response> saveRule(T rule) throws ValidationException, NoSuchGroupException;
    ResponseEntity<Response> deleteRule(Long id) throws NoSuchRuleException;
    ResponseEntity<Response> getRules();
}

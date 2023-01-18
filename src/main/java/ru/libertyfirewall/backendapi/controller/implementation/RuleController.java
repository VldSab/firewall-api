package ru.libertyfirewall.backendapi.controller.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.controller.LibertyController;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleExeption;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.service.implementation.RuleServiceStandard;


@RestController
@RequestMapping("/rules")
@RequiredArgsConstructor
public class RuleController extends LibertyController {
    private final RuleServiceStandard ruleServiceStandard;

    @PostMapping
    public ResponseEntity<Response> saveRule(@RequestBody @Valid Rule rule) {
        Rule ruleCreated;
        String message;
        HttpStatus status;
        try {
           ruleCreated = ruleServiceStandard.create(rule);
           message = "Правило создано";
           status = HttpStatus.OK;
        } catch (ValidationException e) {
           message = e.getMessage();
           status = HttpStatus.BAD_REQUEST;
           ruleCreated = null;
        }
        return createResponse(status, message, ruleCreated);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Response> deleteRule(@PathVariable("id") Long id) {
        boolean isDeleted;
        String message;
        HttpStatus status;

        try {
            isDeleted = ruleServiceStandard.delete(id);
            message = "Правило удалено";
            status = HttpStatus.OK;
        } catch (NoSuchRuleExeption e) {
            isDeleted = false;
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return createResponse(status, message, isDeleted);
    }

    @GetMapping
    public ResponseEntity<Response> getRules() {
        return createResponse(HttpStatus.OK, "Правила получены", ruleServiceStandard.list());
    }


}

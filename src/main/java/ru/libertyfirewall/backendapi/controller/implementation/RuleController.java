package ru.libertyfirewall.backendapi.controller.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.controller.LibertyController;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.service.implementation.RuleServiceStandard;


@RestController
@RequestMapping("/rules")
@RequiredArgsConstructor
@CrossOrigin
public class RuleController extends LibertyController {
    private final RuleServiceStandard ruleServiceStandard;

    @PostMapping
    public ResponseEntity<Response> saveRule(@RequestBody @Valid Rule rule) throws ValidationException {
        return createResponse(HttpStatus.OK, "Правило создано", ruleServiceStandard.create(rule));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Response> deleteRule(@PathVariable("id") Long id) throws NoSuchRuleException {
        return createResponse(HttpStatus.OK, "Правило удалено", ruleServiceStandard.delete(id));
    }

    @GetMapping
    public ResponseEntity<Response> getRules() {
        return createResponse(HttpStatus.OK, "Правила получены", ruleServiceStandard.list());
    }


}

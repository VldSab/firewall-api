package ru.libertyfirewall.backendapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleExeption;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.service.implementation.RuleServiceStandard;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/rule")
@RequiredArgsConstructor
public class RuleController {
    private final RuleServiceStandard ruleServiceStandard;

    @PostMapping("/save")
    public ResponseEntity<Response> saveRule(@RequestBody @Valid Rule rule) {
        boolean isValidRule = true;
        if (rule.getSrcIPs() == null && rule.getSrcGroupID() == null
                || rule.getDstIPs() == null && rule.getDstGroupID() == null)
            isValidRule = false;

        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(isValidRule ? Map.of("rule", ruleServiceStandard.create(rule)) : Map.of("rule", null))
                        .message(isValidRule ? "Rule created" : "Invalid rule")
                        .status(isValidRule ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                        .statusCode(isValidRule ? HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteRule(@PathVariable("id") Long id) {
        boolean isDeleted;
        Map<String, Boolean> data;
        try {
            isDeleted = ruleServiceStandard.delete(id);
            data = Map.of("isDeleted", isDeleted);
        } catch (NoSuchRuleExeption e) {
            data = Map.of(e.getMessage(), false);
        }
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(data)
                        .message("Rule deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getRules() {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("rules", ruleServiceStandard.list()))
                        .message("Rules fetched")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}

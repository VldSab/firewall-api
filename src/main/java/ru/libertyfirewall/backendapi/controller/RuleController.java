package ru.libertyfirewall.backendapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.model.Server;
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
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("rule", ruleServiceStandard.create(rule)))
                        .message("Rule created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteRule(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("isDeleted", ruleServiceStandard.delete(id)))
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

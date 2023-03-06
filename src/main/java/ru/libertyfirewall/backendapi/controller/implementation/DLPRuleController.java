package ru.libertyfirewall.backendapi.controller.implementation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.controller.LibertyController;
import ru.libertyfirewall.backendapi.controller.RuleController;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.rules.DLPRule;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.service.implementation.DLPRuleService;

@RestController
@RequestMapping("/dlp")
@CrossOrigin
public class DLPRuleController extends LibertyController implements RuleController<DLPRule> {

    private DLPRuleService dlpRuleService;

    @PostMapping
    @Override
    public ResponseEntity<Response> saveRule(@RequestBody @Valid DLPRule rule) throws NoSuchGroupException {
        return createResponse(HttpStatus.OK, "DLP-правило сохранено", dlpRuleService.create(rule));
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Response> deleteRule(Long id) throws NoSuchRuleException {
        return createResponse(HttpStatus.OK, "DLP-правило удалено", dlpRuleService.delete(id));
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getRules() {
        return createResponse(HttpStatus.OK, "DLP-правила получены", dlpRuleService.list());
    }
}

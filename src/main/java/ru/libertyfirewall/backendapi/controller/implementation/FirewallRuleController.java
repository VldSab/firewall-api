package ru.libertyfirewall.backendapi.controller.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.controller.LibertyController;
import ru.libertyfirewall.backendapi.controller.RuleController;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.exeptions.rule.NoSuchRuleException;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.rules.FirewallRule;
import ru.libertyfirewall.backendapi.service.implementation.FirewallRuleService;


@RestController
@RequestMapping("/rules")
@RequiredArgsConstructor
@CrossOrigin
public class FirewallRuleController extends LibertyController implements RuleController<FirewallRule> {
    private final FirewallRuleService firewallRuleService;

    @PostMapping
    @Override
    public ResponseEntity<Response> saveRule(@RequestBody @Valid FirewallRule firewallRule) throws ValidationException {
        return createResponse(HttpStatus.OK, "Правило создано", firewallRuleService.create(firewallRule));
    }

    @DeleteMapping("/id/{id}")
    @Override
    public ResponseEntity<Response> deleteRule(@PathVariable("id") Long id) throws NoSuchRuleException {
        return createResponse(HttpStatus.OK, "Правило удалено", firewallRuleService.delete(id));
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getRules() {
        return createResponse(HttpStatus.OK, "Правила получены", firewallRuleService.list());
    }

}

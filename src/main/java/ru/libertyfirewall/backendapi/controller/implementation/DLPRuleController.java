package ru.libertyfirewall.backendapi.controller.implementation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.controller.LibertyController;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.model.rules.DLPRule;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.service.implementation.DLPRuleService;

@RestController
@RequestMapping("/dlp")
@CrossOrigin
public class DLPRuleController extends LibertyController {

    private DLPRuleService dlpRuleService;

    @PostMapping
    public ResponseEntity<Response> saveDLPRule(@RequestBody @Valid DLPRule dlpRule) throws ValidationException {
        return createResponse(HttpStatus.OK, "DLP-правило сохранено", dlpRuleService.create(dlpRule));
    }

    @GetMapping
    public ResponseEntity<Response> list() {
        return null;
    }
}

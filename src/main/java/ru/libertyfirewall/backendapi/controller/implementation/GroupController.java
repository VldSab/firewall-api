package ru.libertyfirewall.backendapi.controller.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.controller.LibertyController;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupExeption;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.service.implementation.GroupServiceStandard;


@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
@CrossOrigin
public class GroupController extends LibertyController {

    private final GroupServiceStandard groupServiceStandard;

    @PostMapping
    public ResponseEntity<Response> saveGroup(@RequestBody @Valid GroupContainer group) {
        GroupContainer ruleCreated;
        String message;
        HttpStatus status;
        try {
            ruleCreated = groupServiceStandard.create(group);
            message = "Правило создано";
            status = HttpStatus.OK;
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            ruleCreated = null;
        }
        return createResponse(status, message, ruleCreated);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Response> deleteGroup(@PathVariable("id") Long id) {
        boolean data;
        HttpStatus status;
        String message;
        try {
            data = groupServiceStandard.delete(id);
            status = HttpStatus.OK;
            message = "Правило удалено";
        } catch (NoSuchGroupExeption e) {
            data = false;
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        }

        return createResponse(status, message, data);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Response> deleteGroup(@PathVariable("name") String name) {
        boolean data;
        HttpStatus status;
        String message;
        try {
            data = groupServiceStandard.delete(name);
            status = HttpStatus.OK;
            message = "Правило удалено";
        } catch (NoSuchGroupExeption e) {
            data = false;
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        }

        return createResponse(status, message, data);
    }

    @GetMapping
    public ResponseEntity<Response> getGroups() {
        return createResponse(HttpStatus.OK, "Группы получены", groupServiceStandard.list());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response> getGroupsByName(@PathVariable String name) throws NoSuchGroupExeption {
        return createResponse(HttpStatus.OK, "Группа получена", groupServiceStandard.getByName(name));
    }

}


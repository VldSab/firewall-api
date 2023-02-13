package ru.libertyfirewall.backendapi.controller.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.controller.LibertyController;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.service.implementation.GroupServiceStandard;


@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@CrossOrigin
public class GroupController extends LibertyController {

    private final GroupServiceStandard groupServiceStandard;

    @PostMapping
    public ResponseEntity<Response> saveGroup(@RequestBody @Valid GroupContainer group) {
        return createResponse(HttpStatus.OK, "Группа создана", groupServiceStandard.create(group));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Response> deleteGroup(@PathVariable("id") Long id) throws NoSuchGroupException {
        return createResponse(HttpStatus.OK, "Группа удалена", groupServiceStandard.delete(id));
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Response> deleteGroup(@PathVariable("name") String name) throws NoSuchGroupException {
        return createResponse(HttpStatus.OK, "Группа удалена", groupServiceStandard.delete(name));
    }

    @GetMapping
    public ResponseEntity<Response> getGroups() {
        return createResponse(HttpStatus.OK, "Группы получены", groupServiceStandard.list());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response> getGroupsByName(@PathVariable String name) throws NoSuchGroupException {
        return createResponse(HttpStatus.OK, "Группа получена", groupServiceStandard.getByName(name));
    }

}


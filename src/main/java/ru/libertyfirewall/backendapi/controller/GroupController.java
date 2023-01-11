package ru.libertyfirewall.backendapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupExeption;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.service.implementation.GroupServiceStandard;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupServiceStandard groupServiceStandard;

    @PostMapping("/save")
    public ResponseEntity<Response> saveGroup(@RequestBody @Valid GroupContainer group) {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("group", groupServiceStandard.create(group)))
                        .message("Group created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Response> deleteGroup(@PathVariable("id") Long id) {
        boolean isDeleted;
        Map<String, Boolean> data;
        try {
            isDeleted = groupServiceStandard.delete(id);
            data = Map.of("isDeleted", isDeleted);
        } catch (NoSuchGroupExeption e) {
            isDeleted = false;
            data = Map.of(e.getMessage(), false);
        }

        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(data)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message(isDeleted ? "Group deleted" : "Group not deleted")
                        .build()
        );
    }

    @DeleteMapping("/delete/name/{name}")
    public ResponseEntity<Response> deleteGroup(@PathVariable("name") String name) {
        boolean isDeleted;
        Map<String, Boolean> data;
        try {
            isDeleted = groupServiceStandard.delete(name);
            data = Map.of("isDeleted", isDeleted);
        } catch (NoSuchGroupExeption e) {
            isDeleted = false;
            data = Map.of(e.getMessage(), false);
        }

        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(data)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message(isDeleted ? "Group deleted" : "Group not deleted")
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getGroups() {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("rules", groupServiceStandard.list()))
                        .message("Groups fetched")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}


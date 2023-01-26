package ru.libertyfirewall.backendapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.libertyfirewall.backendapi.model.Response;

import java.time.LocalDateTime;

public abstract class LibertyController {
    public ResponseEntity<Response> createResponse(HttpStatus status, String message, Object data) {
        if (status.equals(HttpStatus.OK))
            return ResponseEntity.ok()
                    .header("Access-Control-Allow-Credentials", "true")
                    .body(
                        Response.builder()
                                .time(LocalDateTime.now())
                                .message(message)
                                .status(status)
                                .statusCode(status.value())
                                .data(data)
                                .build()
            );
        if (status.equals(HttpStatus.BAD_REQUEST))
            return ResponseEntity.badRequest()
                    .header("Access-Control-Allow-Credentials", "true")
                    .body(
                    Response.builder()
                            .time(LocalDateTime.now())
                            .status(status)
                            .statusCode(status.value())
                            .build()
            );
        return ResponseEntity.internalServerError()
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }
    public ResponseEntity<?> createRawResponse(HttpStatus status, Object data) {
        if (status.equals(HttpStatus.OK))
            return ResponseEntity.ok(data);
        if (status.equals(HttpStatus.BAD_REQUEST))
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .time(LocalDateTime.now())
                            .status(status)
                            .statusCode(status.value())
                            .build()
            );
        if (status.equals(HttpStatus.NOT_FOUND))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Response.builder()
                            .time(LocalDateTime.now())
                            .status(status)
                            .statusCode(status.value())
                            .build()
            );
        return ResponseEntity.internalServerError().build();
    }
}

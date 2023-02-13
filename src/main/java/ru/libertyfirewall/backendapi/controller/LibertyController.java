package ru.libertyfirewall.backendapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.libertyfirewall.backendapi.exeptions.NotFoundException;
import ru.libertyfirewall.backendapi.exeptions.ValidationException;
import ru.libertyfirewall.backendapi.model.Response;

import java.time.LocalDateTime;
import java.util.Map;

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleNull(final NullPointerException e) {
        return Map.of(
                "error", "Не передан параметр",
                "message", e.getMessage(),
                "status", HttpStatus.BAD_REQUEST.toString(),
                "statusCode", String.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadValidation(final ValidationException e) {
        return Map.of(
                "error", "Ошибка валидации",
                "message", e.getMessage(),
                "status", HttpStatus.BAD_REQUEST.toString(),
                "statusCode", String.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleWrongResource(final NotFoundException e) {
        return Map.of(
                "error", "Ресурс не найден",
                "message", e.getMessage(),
                "status", HttpStatus.NOT_FOUND.toString(),
                "statusCode", String.valueOf(HttpStatus.NOT_FOUND.value())
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleWrongArguments(final Exception e) {
        return Map.of(
                "error", "Не все аргументы переданы, либо переданы с ошибкой",
                "message", e.getMessage(),
                "status", HttpStatus.BAD_REQUEST.toString(),
                "statusCode", String.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

}

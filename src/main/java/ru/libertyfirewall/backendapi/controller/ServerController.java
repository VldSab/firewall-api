package ru.libertyfirewall.backendapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.libertyfirewall.backendapi.enumeration.Status;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.Server;
import ru.libertyfirewall.backendapi.service.implementation.ServerServiceStandart;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerServiceStandart serverServiceStandart;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        ResponseEntity<Response> ok = ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("servers", serverServiceStandart.list(30)))
                        .message("Serverse retrived")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
        return ok;
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverServiceStandart.ping(ipAddress);
        ResponseEntity<Response> ok = ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("servers", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
        return ok;
    }

}

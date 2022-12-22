package ru.libertyfirewall.backendapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.libertyfirewall.backendapi.enumeration.Status;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.model.Server;
import ru.libertyfirewall.backendapi.service.implementation.ServerServiceStandart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                        .message("Server retrieved")
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

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
            Response.builder()
                    .time(LocalDateTime.now())
                    .data(Map.of("server", serverServiceStandart.create(server)))
                    .message("Server created")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        Server server = serverServiceStandart.get(id);
        ResponseEntity<Response> ok = ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .message("Server retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
        return ok;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        ResponseEntity<Response> ok = ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("deleted", serverServiceStandart.delete(id)))
                        .message("Server deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
        return ok;
    }

    @GetMapping(path = "/image/{fileName}", params = MediaType.IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "Desktop"
                + File.separator + "liberty-firewall" + File.separator +  "server-images" + File.separator + fileName ));
    }
}

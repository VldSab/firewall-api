package ru.libertyfirewall.backendapi.controller.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.libertyfirewall.backendapi.model.RedisTest;
import ru.libertyfirewall.backendapi.model.Response;
import ru.libertyfirewall.backendapi.redis.RedisMessagePublisher;
import ru.libertyfirewall.backendapi.redis.RedisMessageSubscriber;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisTestController {

    private final RedisMessagePublisher publisher;

    @GetMapping("/look")
    public ResponseEntity<Response> testGet() {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(Map.of("Message list", RedisMessageSubscriber.messageList))
                        .message("Get from redis")
                        .build()
        );
    }

    @PostMapping("/send")
    public ResponseEntity<Response> testSend(@RequestBody RedisTest redisTest) {
        publisher.publish(redisTest.toString());
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message("Redis list " + RedisMessageSubscriber.messageList)
                        .build()
        );
    }
}

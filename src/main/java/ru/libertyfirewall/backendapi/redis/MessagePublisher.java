package ru.libertyfirewall.backendapi.redis;

public interface MessagePublisher {
    void publish(String message);
}

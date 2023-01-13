package ru.libertyfirewall.backendapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisTest {
    Long redisID;
    String message;

    @Override
    public String toString() {
        return "RedisTest{" +
                "redisID=" + redisID +
                ", message='" + message + '\'' +
                '}';
    }
}

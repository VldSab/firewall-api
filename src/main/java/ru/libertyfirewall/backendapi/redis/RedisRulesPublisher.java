package ru.libertyfirewall.backendapi.redis;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ToString
public class RedisRulesPublisher implements MessagePublisher{

    private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    @Qualifier("topicRules")
    private final ChannelTopic topic;

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}

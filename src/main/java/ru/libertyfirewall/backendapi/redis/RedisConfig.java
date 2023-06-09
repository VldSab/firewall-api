package ru.libertyfirewall.backendapi.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("redis");
        //config.setHostName("localhost");
        config.setPort(6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    public MessagePublisher redisPublisher() {
        return new RedisRulesPublisher(redisTemplate(), topicRules());
    }

    @Bean("topic")
    public ChannelTopic topic() {
        return new ChannelTopic("queue");
    }

    @Bean("topicRules")
    public ChannelTopic topicRules() {
        return new ChannelTopic("rules:queue");
    }

    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber(), "onMessage");
    }

    @Bean
    public RedisMessageListenerContainer messageListenerContainer() {
        RedisMessageListenerContainer messageListenerContainer = new RedisMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory());
        messageListenerContainer.addMessageListener(messageListener(), topicRules());
        return messageListenerContainer;
    }

}

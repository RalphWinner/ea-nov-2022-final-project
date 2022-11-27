package com.ralph.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    public static final String mediaQueue = "mediaQueue";
    public static final String mediaTopicExchange = "mediaTopicExchange";
    public static final String mediaRoutingKey = "mediaRoutingKey";

    @Bean
    public Queue mediaQueue() {
        return new Queue(mediaQueue, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(mediaTopicExchange);
    }

    @Bean
    Binding binding(Queue mediaQueue, TopicExchange exchange) {
        return BindingBuilder.bind(mediaQueue).to(exchange).with(mediaRoutingKey);
    }
}
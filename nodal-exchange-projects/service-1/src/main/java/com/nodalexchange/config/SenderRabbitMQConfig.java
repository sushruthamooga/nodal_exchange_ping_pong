package com.nodalexchange.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderRabbitMQConfig {
    @Value("${publish_queue}")
    public String publishQueue;
    @Value("${publish_exchange}")
    public String publishExchange;

    @Bean
    public Queue senderQueue() {
        return new Queue(publishQueue);
    }
    @Bean
    public DirectExchange senderExchange() {
        return new DirectExchange(publishExchange);
    }
    @Bean
    public Binding senderBinding(Queue senderQueue, DirectExchange senderExchange) {
        return BindingBuilder.bind(senderQueue).to(senderExchange).with("");
    }
}

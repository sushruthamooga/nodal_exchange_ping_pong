package com.nodalexchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverRabbitMQConfig {
    @Value("${consume_queue}")
    public String consumeQueue;
    @Value("${consume_exchange}")
    public String consumeExchange;

    @Bean
    public Queue receiverQueue() {
        return new Queue(consumeQueue);
    }
    @Bean
    public DirectExchange receiverExchange() {
        return new DirectExchange(consumeExchange);
    }
    @Bean
    public Binding receiverBinding(Queue receiverQueue, DirectExchange receiverExchange) {
        return BindingBuilder.bind(receiverQueue).to(receiverExchange).with("");
    }
}

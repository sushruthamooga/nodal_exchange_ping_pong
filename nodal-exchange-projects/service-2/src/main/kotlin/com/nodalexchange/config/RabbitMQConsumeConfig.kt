package com.nodalexchange.com.nodalexchange.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RabbitMQConsumeConfig {
    @Value("\${consume_queue}")
    private lateinit var consumeQueue: String;
    @Value("\${consume_exchange}")
    private lateinit var consumeExchange: String;


    @Bean
    open fun consumerQueue(): Queue {
        return Queue(consumeQueue, true)
    }

    @Bean
    open fun consumerExchange(): TopicExchange {
        return TopicExchange(consumeExchange);
    }

    @Bean
    open fun consumerBinding(consumerQueue: Queue, consumerExchange: TopicExchange): Binding {
        return BindingBuilder.bind(consumerQueue).to(consumerExchange).with("")
    }
}
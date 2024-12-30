package com.nodalexchange.com.nodalexchange.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RabbitMQProduceConfig {
    @Value("\${publish_queue}")
    private lateinit var publishQueue: String;
    @Value("\${publish_exchange}")
    private lateinit var publishExchange: String;


    @Bean
    open fun producerQueue(): Queue {
        return Queue(publishQueue, true)
    }

    @Bean
    open fun producerExchange(): TopicExchange {
        return TopicExchange(publishExchange);
    }

    @Bean
    open fun producerBinding(): Binding {
        return BindingBuilder.bind(producerQueue()).to(producerExchange()).with("")
    }
}
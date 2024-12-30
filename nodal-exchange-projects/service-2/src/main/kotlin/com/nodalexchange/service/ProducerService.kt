package com.nodalexchange.com.nodalexchange.service

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ProducerService(private val rabbitTemplate: RabbitTemplate) {
    @Value("\${publish_exchange}")
    private lateinit var publishExchange: String;
    fun sendMessage(msg: String) {
//        println("Sending message from service-2: $msg")
        rabbitTemplate.convertAndSend(publishExchange, "", msg);
        println("Published message from service-2: $msg")
    }
}
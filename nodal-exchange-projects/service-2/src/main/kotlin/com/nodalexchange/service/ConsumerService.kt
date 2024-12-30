package com.nodalexchange.com.nodalexchange.service

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class ConsumerService(private val producer: ProducerService) {
    @RabbitListener(queues = ["#{'\${consume_queue}'}"])
    fun consumeMessage(message: String){
        println("Received message from service-1: $message")
        if(message.equals("Ping")) {
            producer.sendMessage("Pong")
            Thread.sleep(10000);
            val newMsg = "Ping"
//            println("Sending new message from service-2: $newMsg")
            producer.sendMessage(newMsg)
        }
    }
}
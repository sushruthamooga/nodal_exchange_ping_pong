package com.nodalexchange.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MQConsumeMessage {
    public static Logger logger = LoggerFactory.getLogger(MQConsumeMessage.class);
    private final MQPublishService mqPublishService;

    public MQConsumeMessage(MQPublishService mqPublishService) {
        this.mqPublishService = mqPublishService;
    }
    @RabbitListener(queues = "${consume_queue}")
    public void consumeMessage(String message) throws InterruptedException {
        logger.info("Received message from service-2: " + message);
        if(message.equalsIgnoreCase("Ping")){
            String newMsg = "Pong";
            mqPublishService.sendMessage(newMsg);
            logger.info("Published message from service-1: "+newMsg);
            Thread.sleep(10000);
            newMsg = "Ping";
            mqPublishService.sendMessage(newMsg);
            logger.info("Published message from service-1: "+newMsg);
        }
    }
}

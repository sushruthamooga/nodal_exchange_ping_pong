package com.nodalexchange.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQPublishService {
    public static Logger logger = LoggerFactory.getLogger(MQPublishService.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${publish_exchange}")
    public String publishExchange;

    public MQPublishService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @RabbitListener(queues = "${publish_queue}")
    public void sendMessage(String message) {
        //logger.info("Publishing message: " + message);
        rabbitTemplate.convertAndSend(publishExchange, "", message);
        //logger.info("Published message successfully");
    }
}

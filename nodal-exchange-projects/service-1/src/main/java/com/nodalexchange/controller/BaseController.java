package com.nodalexchange.controller;

import com.nodalexchange.service.MQPublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseController {
    public static Logger logger = LoggerFactory.getLogger(BaseController.class);
    private final MQPublishService mqPublishService;

    public BaseController(MQPublishService mqPublishService) {
        this.mqPublishService = mqPublishService;
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            String message = "Ping";
            logger.info("Spring Application - service 1 started successfully");
            mqPublishService.sendMessage(message);
            logger.info("Published message from service-1: " + message);
        };
    }

}

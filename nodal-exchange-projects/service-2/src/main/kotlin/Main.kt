package com.nodalexchange

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRabbit
open class Service2Application
fun main(args: Array<String>) {
    runApplication<Service2Application>(*args)
}
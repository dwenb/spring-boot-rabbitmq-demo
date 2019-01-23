package com.example.demo.produce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Message {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void send(){
        String id = String.valueOf((Math.random()*100l));
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(id);
        rabbitTemplate.convertAndSend("someQueue","order.aaa","hello world!");
    }


    public void sendMessage(){
        rabbitMessagingTemplate.convertAndSend("someQueue","order.aaa","hello");
    }

}

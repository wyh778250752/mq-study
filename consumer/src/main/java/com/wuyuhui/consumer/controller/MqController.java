package com.wuyuhui.consumer.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/fanout")
    public void Fanout() {
        String exchange = "fanout-exchange";
        String message = "hello, fanout";
        rabbitTemplate.convertAndSend(exchange,"",message);
        System.out.println("消息以发送");
        System.out.println("ok");
    }


    @RequestMapping("/direct")
    public void Direct() {
        String exchange = "direct-exchange1";
        String message = "hello, red";
        rabbitTemplate.convertAndSend(exchange,"red",message);
        System.out.println("消息以发送");
    }



    @RequestMapping("/direct1")
    public void Direct1() {
        String exchange = "direct-exchange1";
        String message = "hello, direct";
        rabbitTemplate.convertAndSend(exchange,"direct",message);
        System.out.println("消息以发送");
    }


    @RequestMapping("/topic")
    public void Topic() {
         String exchange = "topic-exchange";
         String message = "hello 1.topic.w.y.h";
         rabbitTemplate.convertAndSend(exchange,"1.topic.w.y.h",message);
        System.out.println("消息以发送");
    }


    @RequestMapping("/topic1")
    public void Topic1() {
        String exchange = "topic-exchange";
        String message = "hello 1.topic.wyh";
        rabbitTemplate.convertAndSend(exchange,"1.topic.wyh",message);
        System.out.println("消息以发送");
    }

}

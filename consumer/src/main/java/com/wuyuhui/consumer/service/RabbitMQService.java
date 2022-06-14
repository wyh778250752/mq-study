package com.wuyuhui.consumer.service;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class RabbitMQService {
    // 使用配置类完成Fanout模式
    @RabbitListener(queues = "fanout-queue")
    public void listenFanoutQueue(String msg) {
        System.out.println("使用Fanout模式接收到的消息是:" + msg);
    }


    // 使用注解方式完成Direct模式
    @RabbitListener(bindings =@QueueBinding(
            value =@Queue("direct-queue1"),
            exchange =@Exchange(value = "direct-exchange1",type = "direct"),
            key = {"direct","red"}
    ))
    public void listenDirectQueue(String msg) {
        System.out.println("消费者收到direct-queue1的消息:" + msg);
    }


    @RabbitListener(bindings =@QueueBinding(
            value =@Queue("direct-queue2"),
            exchange =@Exchange(value = "direct-exchange1",type = "direct"),
            key = {"direct","yellow"}
    ))
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者收到direct-queue2的消息:" + msg);
    }

    // 使用注解方式完成Topic模式
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic-queue1"),
            exchange = @Exchange(value = "topic-exchange", type = "topic"),
            key = {"*.topic.#"}
    ))
    public void listenerTopicQueue(String msg) {
        System.out.println("消费者收到Topic-queue1的消息:" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic-queue2"),
            exchange = @Exchange(value = "topic-exchange",type = "topic"),
            key = {"*.topic.*"}
    ))
    public void listenerTopicQueue1(String msg) {
        System.out.println("消费者收到Topic-queue2的消息:" + msg);
    }
}

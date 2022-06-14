package com.wuyuhui.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    ApplicationRunner runner(ConnectionFactory cf) {
        return args -> cf.createConnection().close();
    }

    // exchange(交换机)
    // fanout
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    // queue(队列)
    // fanout-queue
    @Bean
    public Queue fanoutQueue() {
        return new Queue("fanout-queue");
    }

    // 绑定交换机和队列
    @Bean
    public Binding bingFanoutExchange() {
        return BindingBuilder
                .bind(fanoutQueue())
                .to(fanoutExchange());
    }



}

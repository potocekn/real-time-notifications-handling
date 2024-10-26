package com.rabbitmqcommons.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQNotificationsConfig {

    public static final String QUEUE_NAME = "NotificationsQueue";
    public static final String EXCHANGE_NAME = "NotificationsExchange";
    public static final String ROUTING_KEY = "notify";

    @Bean
    Queue notificationsQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    Exchange notificationsExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    Binding binding(){
        // return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, TOPIC_NAME, "myTopicExchange", null);
        return BindingBuilder.bind(notificationsQueue()).to(notificationsExchange()).with(ROUTING_KEY).noargs();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        //TODO do through the properties file
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");

        return cachingConnectionFactory;
    }


    @Bean
    public RabbitTemplate customRabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

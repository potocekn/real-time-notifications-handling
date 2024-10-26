package com.rabbitmqlistener.notificationslistener.config;

import com.rabbitmqlistener.notificationslistener.listener.RabbitMqNotificationMessageListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQNotificationsConfig {

    private static final String QUEUE_NAME = "NotificationsQueue";
    private static final String EXCHANGE_NAME = "NotificationsExchange";
    private static final String ROUTING_KEY = "notify";

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
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(notificationsQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMqNotificationMessageListener());

        return simpleMessageListenerContainer;
    }
}

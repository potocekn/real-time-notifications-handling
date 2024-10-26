package com.rabbitmqlistener.notificationslistener.listener;

import com.rabbitmqcommons.config.RabbitMQNotificationsConfig;
import com.rabbitmqcommons.notifications.PostInteractionMessage;
import com.rabbitmqlistener.notificationslistener.config.RabbitMqListenerConfig;
import com.rabbitmqlistener.notificationslistener.processors.NoSuitableProcessorException;
import com.rabbitmqlistener.notificationslistener.processors.NotificationProcessorDispatcher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import static com.rabbitmqcommons.config.RabbitMQNotificationsConfig.QUEUE_NAME;

@Service
@Import({RabbitMQNotificationsConfig.class, RabbitMqListenerConfig.class})
public class CustomRabbitMqNotificationsListener {

    private final NotificationProcessorDispatcher dispatcher;

    CustomRabbitMqNotificationsListener(NotificationProcessorDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @RabbitListener(queues =QUEUE_NAME)
    public void receiveMessage(final PostInteractionMessage customMessage) {
        //System.out.println("Received message and deserialized to 'PostInteractionMessage': " + customMessage.toString());
        try{
            dispatcher.processNotification(customMessage);
        } catch (NoSuitableProcessorException e) {
            System.out.println("An error occured when processing message: " + e.getMessage());
        }
    }
}

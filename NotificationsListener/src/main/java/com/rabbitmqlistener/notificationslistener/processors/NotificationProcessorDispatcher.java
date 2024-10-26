package com.rabbitmqlistener.notificationslistener.processors;

import com.rabbitmqcommons.notifications.PostInteractionMessage;

import java.util.List;

public class NotificationProcessorDispatcher {

    private final List<NotificationProcessor> notificationProcessors;

    public NotificationProcessorDispatcher(List<NotificationProcessor> notificationProcessors) {
        this.notificationProcessors = notificationProcessors;
    }

    public void processNotification(PostInteractionMessage message) throws NoSuitableProcessorException {
        for (NotificationProcessor notificationProcessor : notificationProcessors) {
            if (notificationProcessor.canProcessNotificationType(message.channel())
                    && notificationProcessor.process(message)
            ){
                return;
            }
        }

        throw new NoSuitableProcessorException(message);
    }
}

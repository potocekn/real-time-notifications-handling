package com.rabbitmqlistener.notificationslistener.processors.unknown;

import com.rabbitmqcommons.notifications.NotificationChannel;
import com.rabbitmqcommons.notifications.PostInteractionMessage;
import com.rabbitmqlistener.notificationslistener.processors.NotificationProcessor;

public class UnknownNotificationProcessor implements NotificationProcessor {
    @Override
    public boolean canProcessNotificationType(NotificationChannel channelType) {
        return true;
    }

    @Override
    public boolean process(PostInteractionMessage notification) {
       System.out.println("Unknown Notification processed: " + notification);
       return true;
    }
}

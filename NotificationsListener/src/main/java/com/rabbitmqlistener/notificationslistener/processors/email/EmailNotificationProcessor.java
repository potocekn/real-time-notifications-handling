package com.rabbitmqlistener.notificationslistener.processors.email;

import com.rabbitmqcommons.notifications.NotificationChannel;
import com.rabbitmqcommons.notifications.PostInteractionMessage;
import com.rabbitmqlistener.notificationslistener.processors.NotificationProcessor;

public class EmailNotificationProcessor implements NotificationProcessor {

    @Override
    public boolean canProcessNotificationType(NotificationChannel channelType) {
        return channelType == NotificationChannel.EMAIL;
    }

    @Override
    public boolean process(PostInteractionMessage notification) {
        System.out.println("Email notification processed: " + notification);
        return true;
    }
}

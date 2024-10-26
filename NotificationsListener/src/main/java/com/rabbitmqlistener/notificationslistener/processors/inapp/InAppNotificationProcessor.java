package com.rabbitmqlistener.notificationslistener.processors.inapp;

import com.rabbitmqcommons.notifications.NotificationChannel;
import com.rabbitmqcommons.notifications.PostInteractionMessage;
import com.rabbitmqlistener.notificationslistener.processors.NotificationProcessor;

public class InAppNotificationProcessor implements NotificationProcessor {

    @Override
    public boolean canProcessNotificationType(NotificationChannel channelType) {
        return channelType == NotificationChannel.IN_APP;
    }

    @Override
    public boolean process(PostInteractionMessage notification) {
        System.out.println("InApp notification processed: " +notification);
        return true;
    }
}

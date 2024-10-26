package com.rabbitmqlistener.notificationslistener.processors.sms;

import com.rabbitmqcommons.notifications.NotificationChannel;
import com.rabbitmqcommons.notifications.PostInteractionMessage;
import com.rabbitmqlistener.notificationslistener.processors.NotificationProcessor;

public class SmsNotificationProcessor implements NotificationProcessor {
    @Override
    public boolean canProcessNotificationType(NotificationChannel channelType) {
        return channelType == NotificationChannel.SMS;
    }

    @Override
    public boolean process(PostInteractionMessage notification) {
        System.out.println("SMS notification processed: " + notification);
        return true;
    }
}

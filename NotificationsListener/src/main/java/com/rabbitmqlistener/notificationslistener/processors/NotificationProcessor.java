package com.rabbitmqlistener.notificationslistener.processors;

import com.rabbitmqcommons.notifications.NotificationChannel;
import com.rabbitmqcommons.notifications.PostInteractionMessage;

public interface NotificationProcessor {

    boolean canProcessNotificationType(NotificationChannel channelType);

    boolean process(PostInteractionMessage notification);
}

package com.rabbitmqlistener.notificationslistener.notification;

import java.io.Serializable;

public record PostInteractionMessage(
        String notificationId,
        NotificationType type,
        String userId,
        String content,
        NotificationPriority priority,
        NotificationChannel channel
) implements Serializable {

    @Override
    public String toString() {
        return "PostInteractionMessage{" +
                "notificationId='" + notificationId + '\'' +
                ", type=" + type +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", priority=" + priority +
                ", channel=" + channel +
                '}';
    }
}

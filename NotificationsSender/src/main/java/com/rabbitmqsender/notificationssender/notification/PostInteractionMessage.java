package com.rabbitmqsender.notificationssender.notification;

import java.io.Serializable;

public class PostInteractionMessage implements Serializable {

    private final String notificationId;
    private final NotificationType type;
    private final String userId;
    private final String content;
    private final NotificationPriority priority;
    private final NotificationChannel channel;

    private PostInteractionMessage(String notificationId, NotificationType type, String userId, String content, NotificationPriority priority, NotificationChannel channel) {
        this.notificationId = notificationId;
        this.type = type;
        this.userId = userId;
        this.content = content;
        this.priority = priority;
        this.channel = channel;
    }

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

    public static class PostInteractionMessageBuilder {
        private String notificationId;
        private NotificationType type = NotificationType.COMMENT;
        private String userId;
        private String content;
        private NotificationPriority priority = NotificationPriority.LOW;
        private NotificationChannel channel = NotificationChannel.IN_APP;

        public PostInteractionMessageBuilder withNotificationId(String notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public PostInteractionMessageBuilder withType(NotificationType type) {
            this.type = type;
            return this;
        }

        public PostInteractionMessageBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public PostInteractionMessageBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public PostInteractionMessageBuilder withPriority(NotificationPriority priority) {
            this.priority = priority;
            return this;
        }

        public PostInteractionMessageBuilder withChannel(NotificationChannel channel) {
            this.channel = channel;
            return this;
        }

        public PostInteractionMessage build() {
            return new PostInteractionMessage(notificationId, type, userId, content, priority, channel);
        }
    }
}

package com.rabbitmqcommons.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record PostInteractionMessage(
        @JsonProperty("notification_id") String notificationId,
        @JsonProperty("type") InteractionType type,
        @JsonProperty("user_id")String userId,
        @JsonProperty("content")String content,
        @JsonProperty("priority")NotificationPriority priority,
        @JsonProperty("channel")NotificationChannel channel
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

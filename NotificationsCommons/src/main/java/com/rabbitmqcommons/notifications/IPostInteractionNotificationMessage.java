package com.rabbitmqcommons.notifications;

import java.io.Serializable;

/**
 * Common interface representing the notification about post interaction.
 */
public interface IPostInteractionNotificationMessage extends Serializable {

    /**
     * @return notification ID
     */
    String getNotificationId();

    /**
     * @return the type of post interaction
     */
    InteractionType getType();

    /**
     * @return ID of user who interacted with the post
     */
    String getUserId();

    /**
     * @return the description for post owner to describe what happened
     */
    String getContent();

    /**
     * @return the priority of the notification
     */
    NotificationPriority getPriority();

    /**
     * @return the channel that should process the notification
     */
    NotificationChannel getChannel();
}

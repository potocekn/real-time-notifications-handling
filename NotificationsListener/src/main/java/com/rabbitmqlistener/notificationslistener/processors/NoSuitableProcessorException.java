package com.rabbitmqlistener.notificationslistener.processors;

import com.rabbitmqcommons.notifications.PostInteractionMessage;

public class NoSuitableProcessorException extends Exception {

    public NoSuitableProcessorException(PostInteractionMessage message) {
        super("No suitable processor has been found for message channel type: " + message.channel());
    }
}

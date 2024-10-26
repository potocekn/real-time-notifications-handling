package com.rabbitmqlistener.notificationslistener.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

//public class RabbitMqNotificationMessageListener implements MessageListener {
//
//    @Override
//    public void onMessage(Message message) {
//        //TODO re-route the messages based on the channel type
//        System.out.println("------------------------------------------------------------");
//        System.out.println("message = [" + new String(message.getBody()) + "]");
//        System.out.println("------------------------------------------------------------");
//    }
//}

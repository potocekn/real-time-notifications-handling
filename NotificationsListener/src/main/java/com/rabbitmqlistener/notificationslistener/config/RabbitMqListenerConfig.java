package com.rabbitmqlistener.notificationslistener.config;

import com.rabbitmqlistener.notificationslistener.processors.NotificationProcessorDispatcher;
import com.rabbitmqlistener.notificationslistener.processors.email.EmailNotificationProcessor;
import com.rabbitmqlistener.notificationslistener.processors.inapp.InAppNotificationProcessor;
import com.rabbitmqlistener.notificationslistener.processors.sms.SmsNotificationProcessor;
import com.rabbitmqlistener.notificationslistener.processors.unknown.UnknownNotificationProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMqListenerConfig {

    @Bean
    EmailNotificationProcessor emailNotificationProcessor() {
        return new EmailNotificationProcessor();
    }

    @Bean
    InAppNotificationProcessor inAppNotificationProcessor() {
        return new InAppNotificationProcessor();
    }

    @Bean
    SmsNotificationProcessor smsNotificationProcessor() {
        return new SmsNotificationProcessor();
    }

    @Bean
    UnknownNotificationProcessor unknownNotificationProcessor() {
        return new UnknownNotificationProcessor();
    }

    @Bean
    NotificationProcessorDispatcher dispatcher(){
        return new NotificationProcessorDispatcher(
                List.of(
                    emailNotificationProcessor(),
                    inAppNotificationProcessor(),
                    smsNotificationProcessor(),
                    unknownNotificationProcessor()
                )
        );
    }
}

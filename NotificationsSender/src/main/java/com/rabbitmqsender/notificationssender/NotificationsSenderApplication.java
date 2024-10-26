package com.rabbitmqsender.notificationssender;

import com.rabbitmqsender.notificationssender.notification.NotificationChannel;
import com.rabbitmqsender.notificationssender.notification.NotificationPriority;
import com.rabbitmqsender.notificationssender.notification.NotificationType;
import com.rabbitmqsender.notificationssender.notification.PostInteractionMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationsSenderApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(NotificationsSenderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PostInteractionMessage message = new PostInteractionMessage.PostInteractionMessageBuilder()
				.withNotificationId("testNotificationID")
				.withChannel(NotificationChannel.EMAIL)
				.withPriority(NotificationPriority.MEDIUM)
				.withType(NotificationType.COMMENT)
				.withContent("Test content of notification")
				.withUserId("testUserID")
				.build();

		rabbitTemplate.convertAndSend("NotificationsExchange", "notify", message);
	}
}

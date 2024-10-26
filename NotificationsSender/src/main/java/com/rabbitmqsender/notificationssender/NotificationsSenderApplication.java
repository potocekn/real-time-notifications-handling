package com.rabbitmqsender.notificationssender;

import com.rabbitmqcommons.config.RabbitMQNotificationsConfig;
import com.rabbitmqcommons.notifications.InteractionType;
import com.rabbitmqcommons.notifications.NotificationChannel;
import com.rabbitmqcommons.notifications.NotificationPriority;
import com.rabbitmqcommons.notifications.PostInteractionMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootApplication
@Import(RabbitMQNotificationsConfig.class)
public class NotificationsSenderApplication implements CommandLineRunner {

	private final RabbitTemplate customRabbitTemplate;

	public NotificationsSenderApplication(@Autowired RabbitTemplate customRabbitTemplate) {
		this.customRabbitTemplate = customRabbitTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationsSenderApplication.class, args);
	}

	private PostInteractionMessage createEmailMessage(){
		return new PostInteractionMessage(
				"testNotificationID",
				InteractionType.COMMENT,
				"testUserID",
				"Test content of email notification",
				NotificationPriority.MEDIUM,
				NotificationChannel.EMAIL
		);
	}

	private PostInteractionMessage createSmsMessage(){
		return new PostInteractionMessage(
				"testNotificationID2",
				InteractionType.LIKE,
				"testUserID2",
				"Test content of sms notification",
				NotificationPriority.LOW,
				NotificationChannel.SMS
		);
	}

	private PostInteractionMessage createInAppMessage(){
		return new PostInteractionMessage(
				"testNotificationID3",
				InteractionType.LIKE,
				"testUserID3",
				"Test content of in-app notification",
				NotificationPriority.HIGH,
				NotificationChannel.IN_APP
		);
	}



	private PostInteractionMessage createUnknownMessage(){
		return new PostInteractionMessage(
				"testNotificationID4",
				InteractionType.COMMENT,
				"testUserID4",
				"Test content of unknown notification",
				NotificationPriority.LOW,
				null
		);
	}

	private List<PostInteractionMessage> createMessages(){
		return List.of(createEmailMessage(), createSmsMessage(), createInAppMessage(), createUnknownMessage());
	}

	@Override
	public void run(String... args) throws Exception {
		createMessages().forEach(message -> customRabbitTemplate.convertAndSend("NotificationsExchange", "notify", message));
	}
}

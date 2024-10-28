package com.rabbitmqlistener.notificationslistener.processors.email;

import com.rabbitmqcommons.notifications.NotificationChannel;
import com.rabbitmqcommons.notifications.PostInteractionMessage;
import com.rabbitmqlistener.notificationslistener.processors.NotificationProcessor;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailNotificationProcessor implements NotificationProcessor {

    @Override
    public boolean canProcessNotificationType(NotificationChannel channelType) {
        return channelType == NotificationChannel.EMAIL;
    }

    @Override
    public boolean process(PostInteractionMessage notification) {
        Email from = new Email("testCompanyMail@company.com");
        Email to = new Email("customerEmail@customer.com");
        Content content = new Content("text/plain", notification.content());
        Mail mail = new Mail(from, notification.priority() + " order processing", to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            System.out.println("Error when processing email notification: " + e.getMessage());
            return false;
        }

        System.out.println("Email notification processed: " + notification);
        return true;
    }
}

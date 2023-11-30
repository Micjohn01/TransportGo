package michaelJohn.transportgo.notification;

import michaelJohn.transportgo.data.dtos.request.EmailNotificationRequest;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}

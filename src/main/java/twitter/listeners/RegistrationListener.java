package twitter.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import twitter.beans.User;
import twitter.events.OnRegistrationCompleteEvent;
import twitter.service.user.UserService;

import java.util.UUID;

/**
 * Created by moluram on 25.3.17.
 */
@Component
public class RegistrationListener
    implements ApplicationListener<OnRegistrationCompleteEvent> {
  private UserService service;
  private MessageSource messages;
  private MailSender mailSender;

  @Override
  public void onApplicationEvent(OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(OnRegistrationCompleteEvent event) {
    User user = event.getUser();
    String token = UUID.randomUUID().toString();
    service.createVerificationToken(user, token);

    String recipientAddress = user.getEmail();
    String subject = "Registration Confirmation";
    String confirmationUrl = event
        .getAppUrl() + "/registration/confirm.html?token=" + token;
    String message = messages.getMessage("message.regSucc",
        null, event.getLocale());

    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message + " rn" + event.getAppUrl() + confirmationUrl);
    mailSender.send(email);
  }

  @Autowired
  @Qualifier("userService")
  public void setService(UserService service) {
    this.service = service;
  }

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @Autowired
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }
}

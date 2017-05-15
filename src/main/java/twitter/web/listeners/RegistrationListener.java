package twitter.web.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import twitter.beans.User;
import twitter.service.user.UserService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.MessagesConstant;
import twitter.web.constants.URLConstants;
import twitter.web.constants.WebConstants;
import twitter.web.events.OnRegistrationCompleteEvent;

import java.util.UUID;

/**
 * Serve for sending registration token to the user
 *
 * @author moluram
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
  private UserService service;
  private MessageSource messages;
  private MailSender mailSender;

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

  @Override
  public void onApplicationEvent(OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  /**
   * Sending email to user from the event
   *
   * @param event - carries information needed to sending email
   */
  private void confirmRegistration(OnRegistrationCompleteEvent event) {
    User user = event.getUser();
    String token = UUID.randomUUID().toString();
    service.createVerificationToken(user, token);
    String recipientAddress = user.getEmail();
    String subject = messages.getMessage(MessagesConstant.RESEND_TOKEN_TITLE, null, event.getLocale());
    String confirmationUrl = event.getAppUrl() + WebConstants.SLASH + URLConstants.SIGNUP_PAGE
        + WebConstants.SLASH + URLConstants.CONFIRM_REGISTRATION
        + "?" + AttributeNamesConstants.REGISTRATION_TOKEN + "=" + token;
    String message = messages.getMessage(MessagesConstant.REG_SUCC ,
        null, event.getLocale());
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message + "/r/n" + confirmationUrl);
    mailSender.send(email);
  }
}

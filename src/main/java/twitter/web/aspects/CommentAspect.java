package twitter.web.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import twitter.beans.User;
import twitter.web.dto.CommentDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by moluram on 12.5.17.
 */
@Aspect
@Component
public class CommentAspect {
  private MailSender mailSender;
  private MessageSource messages;
  private Environment env;

  @Autowired
  public void setEnv(Environment env) {
    this.env = env;
  }

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @Autowired
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @After(value = "twitter.web.controllers.CommentController.addComment(request, user, commentDto)",
      argNames = "request,user,commentDto")
  public void sendMessage(HttpServletRequest request, User user, CommentDto commentDto) {
    mailSender.send(this.constructEmail(request, user,commentDto));
  }

  private SimpleMailMessage constructEmail(HttpServletRequest request, User user , CommentDto commentDto) {
    String message1 = messages.getMessage("message.commented.text.part1",null, request.getLocale());
    String message2 = messages.getMessage("message.commented.text.part2",null, request.getLocale());
    return constructEmail(messages.getMessage("message.commented.subject", null, request.getLocale()) ,
        message1 + commentDto.getAuthor() + message2 +"\r\n" + commentDto.getText() , user);
  }

  private SimpleMailMessage constructEmail(String subject, String body,
                                           User user) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(subject);
    email.setText(body);
    email.setTo(user.getEmail());
    email.setFrom(env.getProperty("support.email"));
    return email;
  }
}

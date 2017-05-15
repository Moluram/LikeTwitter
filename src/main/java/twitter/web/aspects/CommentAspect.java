package twitter.web.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import twitter.beans.User;
import twitter.service.user.UserService;
import twitter.web.constants.MessagesConstant;
import twitter.web.dto.CommentDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Service serve reacting on a comment
 *
 * @author Aliaksei Chorny
 */
@Aspect
@Component
public class CommentAspect {
  private MailSender mailSender;
  private MessageSource messages;
  private Environment env;
  private UserService userService;

  @Autowired
  @Qualifier("userService")
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

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

  /**
   * Sending email to user about new comment
   * @param request - request in which comment was added
   * @param commentDto - added comment
   */
  @After(value = "twitter.web.controllers.CommentController.addComment(request, commentDto)",
      argNames = "request,commentDto")
  public void sendMessage(HttpServletRequest request, CommentDto commentDto) {
    mailSender.send(this.constructEmail(request, commentDto));
  }

  private SimpleMailMessage constructEmail(HttpServletRequest request, CommentDto commentDto) {
    String message1 = messages.getMessage(MessagesConstant.MESSAGE_COMMENTED_TEXT_PART1,null, request.getLocale());
    String message2 = messages.getMessage(MessagesConstant.MESSAGE_COMMENTED_TEXT_PART2,null, request.getLocale());
    return constructEmail(messages.getMessage(MessagesConstant.MESSAGE_COMMENTED_SUBJECT, null, request.getLocale()) ,
        message1 + commentDto.getAuthor() + message2 +"\r\n" + commentDto.getText() ,
        userService.getUserByUsername(commentDto.getAuthor()));
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

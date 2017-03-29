package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.service.user.UserService;
import twitter.web.beans.GenericResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Moluram on 3/28/2017.
 */
@Controller
@RequestMapping("/${username}")
public class UserController {
  private UserService userService;
  private MessageSource messages;
  private Environment env;


  @Autowired
  @Qualifier("userService")
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }


}

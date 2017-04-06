package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter.service.user.UserService;

/**
 * Created by Moluram on 3/28/2017.
 */
@Controller
@RequestMapping("/{username}")
public class UserController {
  private UserService userService;
  private MessageSource messages;
  private Environment env;


  @Autowired
  @Qualifier("userService")
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @RequestMapping(method = RequestMethod.GET)
  public String getHomepage() {
    return "homepage";
  }

}

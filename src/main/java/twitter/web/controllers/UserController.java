package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
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

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getHomepage(@PathVariable String username, ModelAndView model) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      model.setViewName("errors/404error");
      return model;
    }
    model.setViewName("homepage");
    return model;
  }

}

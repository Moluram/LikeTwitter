package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter.entity.User;
import twitter.service.user.UserService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.PageNamesConstants;
import twitter.web.constants.URLConstants;
import twitter.web.constants.WebConstants;

import javax.servlet.http.HttpSession;

/**
 * Controller is used to process login
 *
 * @author moluram
 */
@Controller
@RequestMapping(value = WebConstants.SLASH + URLConstants.SIGNIN_PAGE)
public class LoginController {
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String login() {
    return PageNamesConstants.SIGNIN_PAGE;
  }

  /**
   * Redirects user to his homepage. Login processed in spring container
   *
   * @return redirect to user homepage
   */
  @RequestMapping(method = RequestMethod.POST)
  public String performLogin(HttpSession session) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByUsername(auth.getName());
    session.setAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME, user);
    return WebConstants.REDIRECT + auth.getName();
  }
}
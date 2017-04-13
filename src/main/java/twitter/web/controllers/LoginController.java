package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import twitter.beans.User;
import twitter.service.user.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by moluram on 23.3.17.
 */
@Controller
public class LoginController {
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public String login(Model model, String error, String logout, Authentication auth) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");
    return "signin";
  }

  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  public String performLogin
      (WebRequest request, HttpSession session) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByUsername(auth.getName());
    session.setAttribute("user", user);
    return "redirect:/" + auth.getName() + "?lang=" + request.getLocale().getCountry();
  }
}
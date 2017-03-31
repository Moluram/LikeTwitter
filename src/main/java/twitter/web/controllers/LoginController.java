package twitter.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by moluram on 23.3.17.
 */
@Controller
@PreAuthorize("hasRole('IS_AUTHENTICATED_ANONYMOUSLY')")
public class LoginController {
  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public String login(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");

    return "signin";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(ModelAndView model, WebRequest request) {
    return "redirect:/?lang=" + request.getLocale().getCountry();
  }
}
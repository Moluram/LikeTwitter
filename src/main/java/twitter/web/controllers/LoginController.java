package twitter.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;

/**
 * Created by moluram on 23.3.17.
 */
@Controller
public class LoginController {
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
      (WebRequest request, Authentication auth) {
    auth = SecurityContextHolder.getContext().getAuthentication();
    return "redirect:/" + auth.getName() + "?lang=" + request.getLocale().getCountry();
  }
}
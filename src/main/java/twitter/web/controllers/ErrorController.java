package twitter.web.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Moluram on 3/29/2017.
 */
@Controller
public class ErrorController {
  @RequestMapping(value = "/emailError", method = RequestMethod.GET)
  public String emailError() {
    return "emailError";
  }

  @RequestMapping(value = "/404", method = RequestMethod.GET)
  public String notFound() {
    return "errors/404error";
  }

  @RequestMapping(value = "/accessDenied")
  public String accessDenied(WebRequest request) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return "redirect:/" + auth.getName() + "?lang=" + request.getLocale().getCountry();
  }
}


package twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Moluram on 3/29/2017.
 */
@Controller
public class ErrorController {
  @RequestMapping(value = "/emailError", method = RequestMethod.GET)
  public String emailError() {
    return "emailError";
  }
}

package twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Moluram on 3/30/2017.
 */
@Controller
public class BadUserController {
  @RequestMapping(value = "/bad-user",method = RequestMethod.GET)
  public String badUser() {
    return "badUser";
  }
}

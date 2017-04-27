package twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Moluram on 4/27/2017.
 */
@Controller
public class SupportController {
  @RequestMapping(value = "/about", method = RequestMethod.GET)
  public String getAbout() {
    return "about";
  }

  @RequestMapping(value = "/contact", method = RequestMethod.GET)
  public String getContact() {
    return "contact";
  }
}

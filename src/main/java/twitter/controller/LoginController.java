package twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by moluram on 23.3.17.
 */
@Controller
public class LoginController {
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView sayHelloAgain(ModelAndView model) {
    model.setViewName("login");
    return model;
  }
}

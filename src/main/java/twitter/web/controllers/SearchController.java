package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
import twitter.service.user.UserService;

import java.util.List;

/**
 * Created by Moluram on 4/17/2017.
 */
@Controller
@RequestMapping("/search")
public class SearchController {
  private static final Integer MAX_SUGGESTIONS = 10;
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String getUserPage(@RequestParam("username") String username, ModelAndView model) {
    User user = userService.getUserByUsername(username);
    if (user != null) {
      return "redirect:/" + username;
    }

    model.addObject("usernames", userService.getUsernamesWith(username, MAX_SUGGESTIONS));
    return "suggestionPage";
  }
}

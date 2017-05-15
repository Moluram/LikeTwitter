package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
import twitter.service.user.UserService;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Moluram on 4/17/2017.
 */
@RestController
public class SearchController {
  private static final Integer MAX_SUGGESTIONS = 5;
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public @ResponseBody List getUserPage(@RequestParam("username") String username) throws IOException {
    List<String> list = userService.getUsernamesStartsWith(username, MAX_SUGGESTIONS);
    if (list.isEmpty()) {
      list = userService.getUsernamesContains(username, MAX_SUGGESTIONS);
    }
    return list;
  }
}

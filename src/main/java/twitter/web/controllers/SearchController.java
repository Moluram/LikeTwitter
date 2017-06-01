package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter.service.user.UserService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.URLConstants;
import twitter.web.constants.WebConstants;

import java.io.IOException;
import java.util.List;

/**
 * Created by Moluram on 4/17/2017.
 */
@Controller
public class SearchController {
  private static final Integer MAX_SUGGESTIONS = 5;
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.SEARCH, method = RequestMethod.GET)
  public @ResponseBody List getUserPage(@RequestParam(AttributeNamesConstants.SEARCH_USERNAME) String username) throws IOException {
    List<String> list = userService.getUsernamesStartsWith(username, MAX_SUGGESTIONS);
    if (list.isEmpty()) {
      list = userService.getUsernamesContains(username, MAX_SUGGESTIONS);
    }
    return list;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.SEARCH, method = RequestMethod.POST)
  public String redirect(@RequestParam(AttributeNamesConstants.SEARCH_USERNAME) String username) throws IOException {
    return WebConstants.REDIRECT + username;
  }
}

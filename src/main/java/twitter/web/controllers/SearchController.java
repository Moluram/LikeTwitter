package twitter.web.controllers;

import org.codehaus.jackson.map.ObjectMapper;
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
  private static final Integer MAX_SUGGESTIONS = 10;
  private UserService userService;
  private ObjectMapper objectMapper;



  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody String getUserPage() throws IOException {
    List<String> list = userService.getUsernames();
    return objectMapper.writeValueAsString(list);
  }

  private String[] toArray(List<String> list) {
    String[] array = new String[list.size()];
    Iterator<String> iterator = list.iterator();
    for (int i = 0; i < list.size(); i++) {
      array[i] = iterator.next();
    }
    return array;
  }
}

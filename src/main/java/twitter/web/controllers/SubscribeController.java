package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import twitter.entity.Subscribe;
import twitter.entity.User;
import twitter.service.subscribe.SubscribeService;
import twitter.service.user.UserService;
import twitter.web.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by moluram on 9.5.17.
 */
@Controller
@RequestMapping("/subscribe")
public class SubscribeController {

  private SubscribeService subscribeService;
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setSubscribeService(SubscribeService subscribeService) {
    this.subscribeService = subscribeService;
  }

  @RequestMapping(value = "/{username}",method = POST)
  public @ResponseBody
  Boolean subscribe(@PathVariable String username, @SessionAttribute("user") User sessionUser) {
    Subscribe subscribe = subscribeService.getSubscribe(sessionUser.getUsername());
    boolean answer = false;
    if (subscribe.contains(username)) {
      subscribe.removeSubscribe(username);
      answer = true;
    } else {
      subscribe.addSubscribe(username);
    }
    subscribeService.saveSubscribe(subscribe);
    return answer;
  }

  @RequestMapping(method = GET)
  public ModelAndView listOfSubscribes(@SessionAttribute ("user") User  sessionUser, ModelAndView model) {
    Subscribe subscribe = subscribeService.getSubscribe(sessionUser.getUsername());
    List<UserDto> dtos = new ArrayList<>();
    for (String userUsername: subscribe.getSubscribes()) {
      dtos.add(new UserDto(userService.getUserByUsername(userUsername)));
    }
    model.addObject("users", dtos);
    model.setViewName("subscribes");
    return model;
  }
}

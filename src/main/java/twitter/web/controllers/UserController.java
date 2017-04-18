package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.Tweet;
import twitter.beans.User;
import twitter.service.tweet.TweetService;
import twitter.service.user.UserService;
import twitter.web.dto.TweetDto;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Moluram on 3/28/2017.
 */
@Controller
@RequestMapping("/{username}")
public class UserController {
  private static final String TWEET_DTO_NAME = "tweet";
  private UserService userService;
  private MessageSource messages;
  private Environment env;



  private TweetService tweet_service;


  @Autowired
  public void setTweetS2ervice(TweetService ts ) {
    tweet_service = ts;
  }

  @Autowired
  @Qualifier("userService")
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getHomepage(@PathVariable String username, ModelAndView model,
                                  HttpSession session) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      model.setViewName("errors/404error");
      return model;
    }

    User sessionUser = (User) session.getAttribute("user");
    if (user.equals(sessionUser)) {
      model.addObject("isOwner", true);
      model.addObject(TWEET_DTO_NAME, new TweetDto());
    } else {
      model.addObject("isOwner", false);
    }

    model.addObject("isEnabled", sessionUser.isEnabled());
    model.addObject("tweets", tweet_service.getUserTweets(username));
    model.setViewName("homepage");
    return model;
  }


  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  @RequestMapping(method=RequestMethod.POST)
  public String addTweet(@PathVariable String username,
                         @ModelAttribute(TWEET_DTO_NAME) @Valid TweetDto tweetDto,
                         BindingResult result,
                         @SessionAttribute("user") User sessionUser,
                         WebRequest request) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      return "redirect:/404" + "?lang=" + request.getLocale().getCountry();
    }
    if (!result.hasErrors() && username.equals(sessionUser.getUsername())) {
      tweet_service.addTweet(tweetDto, username);
    }
    return "redirect:/" + username + "?lang=" + request.getLocale().getCountry();
  }
}

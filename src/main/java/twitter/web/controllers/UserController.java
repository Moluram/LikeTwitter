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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import twitter.beans.Subscribe;
import twitter.beans.Tweet;
import twitter.beans.User;
import twitter.service.image.ImageService;
import twitter.service.storage.FileNamingService;
import twitter.service.storage.StorageService;
import twitter.service.subscribe.SubscribeService;
import twitter.service.tweet.TweetService;
import twitter.service.user.UserService;
import twitter.web.dto.TweetDto;
import twitter.web.dto.UserDto;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
  private ImageService imageService;
  private SubscribeService subscribeService;

  @Autowired
  public void setSubscribeService(SubscribeService subscribeService) {
    this.subscribeService = subscribeService;
  }

  @Autowired
  public void setImageService(ImageService imageService) {
    this.imageService = imageService;
  }

  @Autowired
  public void setTweetService(TweetService ts) {
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
    model.addObject("owner" , new UserDto(user));
    User sessionUser = (User) session.getAttribute("user");
    if (user.equals(sessionUser)) {
      model.addObject("isOwner", true);
      model.addObject(TWEET_DTO_NAME, new TweetDto());
    } else {
      model.addObject("isOwner", false);
      boolean isSubscribes = false;
      if (subscribeService.getSubscribe(sessionUser.getUsername()).contains(username)) {
        isSubscribes = true;
      }
      model.addObject("isSubscribes", isSubscribes);
    }

    model.addObject("isEnabled", sessionUser.isEnabled());
    model.addObject("tweets", listOfDto(tweet_service.getUserTweets(username), user));
    model.setViewName("homepage");
    return model;
  }

  private List<TweetDto> listOfDto(List<Tweet> userTweets, User user) {
    List<TweetDto> tweetDtos = new ArrayList<>();
    for ( Tweet tweet: userTweets) {
      tweetDtos.add(new TweetDto(tweet, user));
    }
    return tweetDtos;
  }


  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  @RequestMapping(method = POST)
  public String addTweet(@PathVariable String username,
      @ModelAttribute(TWEET_DTO_NAME) @Valid TweetDto tweetDto,
      BindingResult result,
      @SessionAttribute("user") User sessionUser,
      WebRequest request) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      return "redirect:/404" + "?lang=" + request.getLocale().getCountry();
    }
    if (!result.hasErrors() && username.equals(sessionUser.getUsername()) && !tweetDto.getText()
        .trim().isEmpty()) {
      tweet_service.addTweet(tweetDto, username);
    }
    return "redirect:/" + username + "?lang=" + request.getLocale().getCountry();
  }

  @RequestMapping(value = "/upload-photo", method = POST)
  public String uploadFile(@RequestParam("file") MultipartFile file, WebRequest request,
      @SessionAttribute("user") User sessionUser) {
    if (file.getSize() != 0) {
      imageService.storeImage(file, sessionUser.getUserProfile());
    }
    return "redirect:/" + sessionUser.getUsername() + "?lang=" + request.getLocale().getCountry();
  }

  @RequestMapping(value = "/subscribe", method = POST)
  public String subscribe(@PathVariable String username, WebRequest request,
                          @SessionAttribute ("user") User  sessionUser) {
    Subscribe subscribe = subscribeService.getSubscribe(sessionUser.getUsername());
    if (subscribe.contains(username)) {
      subscribe.removeSubscribe(username);
    } else {
      subscribe.addSubscribe(username);
    }
    //TODO: save in the database
    return "redirect:/" + username + "?lang=" + request.getLocale().getCountry();
  }

  @RequestMapping(value = "/subscribe", method = GET)
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

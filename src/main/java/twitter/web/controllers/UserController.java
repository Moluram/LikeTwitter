package twitter.web.controllers;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import twitter.entity.Tweet;
import twitter.entity.User;
import twitter.service.image.ImageService;
import twitter.service.subscribe.SubscribeService;
import twitter.service.tweet.TweetService;
import twitter.service.user.UserService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.dto.ProfileDto;
import twitter.web.dto.TweetDto;
import twitter.web.dto.UserDto;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller is used to control user flow
 *
 * @author moluram
 */
@Controller
@RequestMapping("/{username}")
public class UserController {
  private static final String TWEET_DTO_NAME = "tweet";

  private UserService userService;
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

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getHomepage(@PathVariable String username, ModelAndView model,
      HttpSession session) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      model.setViewName("404error");
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

    session.setAttribute(AttributeNamesConstants.IS_ENABLED, sessionUser.isEnabled());
    model.addObject("profile", new ProfileDto(user.getUserProfile()));
    model.addObject("tweets", listOfDto(tweet_service.getUserTweets(username), user));
    model.setViewName("homepage");
    return model;
  }

  private List<TweetDto> listOfDto(List<Tweet> userTweets, User user) {
    List<TweetDto> tweetDtos = new ArrayList<>();
    for ( Tweet tweet: userTweets) {
      tweetDtos.add(new TweetDto(tweet, user));
    }
    return Lists.reverse(tweetDtos);
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
      imageService.storeImage(file, sessionUser);
    }
    return "redirect:/" + sessionUser.getUsername() + "?lang=" + request.getLocale().getCountry();
  }
}

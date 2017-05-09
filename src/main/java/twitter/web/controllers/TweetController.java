package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twitter.beans.Tweet;
import twitter.service.tweet.TweetService;

import java.util.List;

/**
 * Created by Moluram on 5/1/2017.
 */
@RestController
@RequestMapping("/tweet")
public class TweetController {
  private TweetService tweetService;

  @Autowired
  public void setTweetService(TweetService tweetService) {
    this.tweetService = tweetService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody Integer addLike(@RequestParam Long id, @RequestParam String username, @RequestParam String owner) {
    List<Tweet> tweetList =  tweetService.getUserTweets(owner);//TODO: Search by id
    Integer number = 0;
    for (Tweet tweet: tweetList) {
      if (tweet.getId().equals(id)) {
        List<String> list = tweet.getUsernamesOfUserWhoLikes();
        if (list.contains(username)) {
          tweet.removeUsernameFromLikes(username);
        } else {
          tweet.addUsernameToLikes(username);
        }
        number = tweet.getUsernamesOfUserWhoLikes().size();
        break;
      }
    }
    return number;
  }
}

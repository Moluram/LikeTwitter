package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter.beans.Tweet;
import twitter.service.tweet.TweetService;

import java.util.List;

/**
 * Created by Moluram on 5/1/2017.
 */
@Controller
@RequestMapping("/tweet")
public class TweetController {
  private TweetService tweetService;

  @Autowired
  public void setTweetService(TweetService tweetService) {
    this.tweetService = tweetService;
  }

  @RequestMapping(value = "/{id}/{username}/{owner}/{tweetOwner}")
  public String addLike(@PathVariable Long id, @PathVariable String username,
                      @PathVariable String owner, @PathVariable String tweetOwner) {
    List<Tweet> tweetList =  tweetService.getUserTweets(tweetOwner);//TODO: Search by id
    for (Tweet tweet: tweetList) {
      if (tweet.getId().equals(id)) {
        List<String> list = tweet.getUsernamesOfUserWhoLikes();
        if (list.contains(username)) {
          tweet.removeUsernameFromLikes(username);
        } else {
          tweet.addUsernameToLikes(username);
        }
        break;
      }
    }
    return "redirect:/" + owner;
  }
}

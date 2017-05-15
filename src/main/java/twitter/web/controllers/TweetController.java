package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter.beans.Tweet;
import twitter.service.tweet.TweetService;

import java.util.List;

/**
 * Responsible for getting output from tweets
 *
 * @author Aliaksei Chorny
 */
@RestController
@RequestMapping("/tweet")
public class TweetController {
    private TweetService tweetService;

    @Autowired
    public void setTweetService(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    /**
     * Adds like from specific user
     *
     * @param id - tweet id
     * @param username - username of user who liked
     * @return number of likes for this tweet
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Integer addLike(@RequestParam Long id, @RequestParam String username) {
        Tweet tweet = tweetService.getTweetById(id);
        List<String> list = tweet.getUsernamesOfUserWhoLikes();
        if (list.contains(username)) {
            tweet.removeUsernameFromLikes(username);
        } else {
            tweet.addUsernameToLikes(username);
        }
        tweetService.updateTweet(tweet);
        return tweet.getUsernamesOfUserWhoLikes().size();
    }
}

package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.Tweet;
import twitter.service.tweet.TweetService;


@Controller
public class TweetController {


    private TweetService tweet_service;


    @Autowired
    public TweetController(TweetService ts ) {
        tweet_service = ts;
    }


    @RequestMapping({"/","/tweets"})
    public ModelAndView showTweets() {
        return new ModelAndView("tweets");
    }


    @RequestMapping(value="/tweets", method=RequestMethod.POST)
    public ModelAndView addTweet(Tweet t){

        this.tweet_service.addTweet(t);

        ModelAndView model = new ModelAndView("tweets");

        model.addObject("allTweets", tweet_service.getList());

        return model;
    }


}
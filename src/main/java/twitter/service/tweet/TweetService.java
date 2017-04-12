package twitter.service.tweet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.beans.Tweet;
import twitter.dao.tweet.TweetDao;


@Service
public class TweetService {

    @Autowired
    private TweetDao tweet_repository;

    public TweetService(){
        super();
    }

    public List<Tweet> getList() {
        return tweet_repository.getList();
    }


    public void addTweet(Tweet t){
        tweet_repository.addTweet(t);
    }

}

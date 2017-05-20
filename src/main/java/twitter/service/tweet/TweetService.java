package twitter.service.tweet;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.entity.Tweet;
import twitter.dao.ITweetDao;
import twitter.web.dto.TweetDto;


@Service
public class TweetService {

    @Autowired
    private ITweetDao tweet_repository;

    public TweetService() {
        super();
    }

    public List<Tweet> getList() {
        return tweet_repository.getAll();
    }


    public void addTweet(Tweet t) {
        t.setDate(Calendar.getInstance().getTime());
        tweet_repository.create(t);
    }

    public List<Tweet> getUserTweets(String username) {
        return tweet_repository.getUserTweets(username);
    }

    public void addTweet(TweetDto tweetDto, String username) {
        Tweet tweet = new Tweet();
        tweet.setDate(Calendar.getInstance().getTime());
        tweet.setOwnerUsername(username);
        tweet.setText(tweetDto.getText());
        tweet_repository.create(tweet);
    }

    public Tweet getTweetById(Long id) {
        return tweet_repository.read(id);
    }

    public void updateTweet(Tweet tweet){
        tweet_repository.update(tweet);
    }
}

package twitter.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Repository;
import twitter.beans.Tweet;

@Repository
public class TweetDao {

    List<Tweet> tweetList = new ArrayList<>();

    public TweetDao(){
        super();
    }

    public List<Tweet> getList(){
        return new ArrayList<Tweet>(tweetList);
    }

    public void addTweet(Tweet t){
        tweetList.add(t);
    }

    public List<Tweet> getUserTweets(String username) {
        List<Tweet> tweets = new ArrayList<>();
        for (Tweet tweet: tweetList) {
            if (tweet.getOwnerUsername().equals(username)) {
                tweets.add(tweet);
            }
        }
        return tweets;
    }
}

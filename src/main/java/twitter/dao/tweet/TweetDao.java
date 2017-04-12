package twitter.dao.tweet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Repository;
import twitter.beans.Tweet;

@Repository
public class TweetDao {

    List<Tweet> tweetList = new ArrayList<Tweet>();

    public TweetDao(){
        super();
    }

    public List<Tweet> getList(){
        return new ArrayList<Tweet>(tweetList);
    }

    public void addTweet(Tweet t){
        t.setDate(Calendar.getInstance().getTime());
        tweetList.add(t);
    }

}

package twitter.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Repository;
import twitter.beans.Tweet;

public interface ITweetDao extends IGenericDAO<Tweet> {
    List<Tweet> getUserTweets(String username);
}

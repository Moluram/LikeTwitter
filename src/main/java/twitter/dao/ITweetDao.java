package twitter.dao;

import java.util.List;

import twitter.entity.Tweet;

public interface ITweetDao extends IGenericDAO<Tweet> {
    List<Tweet> getUserTweets(String username);
}

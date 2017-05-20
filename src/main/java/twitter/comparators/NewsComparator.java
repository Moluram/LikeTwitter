package twitter.comparators;

import twitter.entity.Tweet;

import java.util.Comparator;

/**
 * Serve for comparing the newest news
 *
 * @author moluram
 */
public class NewsComparator implements Comparator<Tweet> {
  @Override
  public int compare(Tweet tweet, Tweet t1) {
    return t1.getDate().compareTo(tweet.getDate());
  }
}

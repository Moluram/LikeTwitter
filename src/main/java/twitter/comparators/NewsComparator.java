package twitter.comparators;

import twitter.beans.Tweet;

import java.util.Comparator;

/**
 * Serve for comparing the newest news
 *
 * @author moluram
 */
public class NewsComparator implements Comparator<Tweet> {
  @Override
  public int compare(Tweet tweet, Tweet t1) {
    return tweet.getDate().compareTo(t1.getDate());
  }
}

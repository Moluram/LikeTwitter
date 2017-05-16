package twitter.service.news;

import twitter.beans.Tweet;
import twitter.beans.User;

import java.util.List;

/**
 * Interface provides access to latest news
 *
 * @author moluram
 */
public interface NewsService {
  List<Tweet> getLatest(User user);
}

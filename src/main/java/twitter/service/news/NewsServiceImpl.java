package twitter.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.entity.Tweet;
import twitter.entity.User;
import twitter.comparators.NewsComparator;
import twitter.service.subscribe.SubscribeService;
import twitter.service.tweet.TweetService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class serve for providing latest news
 *
 * @author moluram
 */
@Component
public class NewsServiceImpl implements NewsService {
  private TweetService tweetService;
  private SubscribeService subscribeService;

  @Autowired
  public void setSubscribeService(SubscribeService subscribeService) {
    this.subscribeService = subscribeService;
  }

  @Autowired
  public void setTweetService(TweetService tweetService) {
    this.tweetService = tweetService;
  }

  @Override
  public List<Tweet> getLatest(User user) {
    List<Tweet> list = getSubscribesTweets(user);
    list.sort(new NewsComparator());
    return list;
  }

  private List<Tweet> getSubscribesTweets(User user) {
    List<Tweet> list = new ArrayList<>();
    for (String username: subscribeService.getSubscribe(user.getUsername()).getSubscribes()) {
      list.addAll(tweetService.getUserTweets(username));
    }
    return list;
  }
}

package twitter.service.subscribe;

import twitter.entity.Subscribe;

/**
 * Created by Moluram on 4/30/2017.
 */
public interface SubscribeService {
  Subscribe getSubscribe(String owner);

  void saveSubscribe(Subscribe subscribe);
}

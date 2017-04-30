package twitter.service.subscribe;

import org.springframework.stereotype.Component;
import twitter.beans.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moluram on 4/30/2017.
 */
@Component
public class SubscribeServiceImpl implements SubscribeService {
  private List<Subscribe> subscribeList = new ArrayList<>();

  public Subscribe getSubscribe(String owner) {
    for (Subscribe subscribe : subscribeList) {
      if (subscribe.getOwnerUsername().equals(owner)) {
        return subscribe;
      }
    }
    Subscribe subscribe = new Subscribe();
    subscribe.setOwnerUsername(owner);
    subscribeList.add(subscribe);
    return subscribe;
  }

  public void saveSubscribe(Subscribe subscribe) {
    if (!subscribeList.contains(subscribe)) {
      subscribeList.add(subscribe);
    }
  }
}

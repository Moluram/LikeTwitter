package twitter.service.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.entity.Subscribe;
import twitter.dao.ISubscribeDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moluram on 4/30/2017.
 */
@Service
public class SubscribeServiceImpl implements SubscribeService {
  private List<Subscribe> subscribeList = new ArrayList<>();

  private final ISubscribeDAO subscribeDAO;

  @Autowired
  public SubscribeServiceImpl(ISubscribeDAO subscribeDAO) {
    this.subscribeDAO = subscribeDAO;
  }

  public Subscribe getSubscribe(String owner) {
    Subscribe subscribe=subscribeDAO.readByOwnerUsername(owner);
    if(subscribe!=null){
      return subscribe;
    }
//    for (Subscribe subscribe : subscribeList) {
//      if (subscribe.getOwnerUsername().equals(owner)) {
//        return subscribe;
//      }
//    }
    subscribe = new Subscribe();
    subscribe.setOwnerUsername(owner);
    subscribeDAO.create(subscribe);
    return subscribe;
  }

  public void saveSubscribe(Subscribe subscribe) {
    Subscribe s=subscribeDAO.read(subscribe.getId());
    if (s==null) {
      subscribeDAO.create(subscribe);
    }else {
      subscribeDAO.update(subscribe);
    }
  }
}

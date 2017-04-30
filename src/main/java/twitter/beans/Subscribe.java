package twitter.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moluram on 4/30/2017.
 */
public class Subscribe {
  private String ownerUsername;
  private List<String> subscribes = new ArrayList<>();

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  public List<String> getSubscribes() {
    return subscribes;
  }

  public void addSubscribe(String subscribes) {
    this.subscribes.add(subscribes);
  }

  public void removeSubscribe(String subscribes) {
    this.subscribes.remove(subscribes);
  }

  public boolean contains(String subscribe) {
    return this.subscribes.contains(subscribe);
  }
}

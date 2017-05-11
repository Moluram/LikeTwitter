package twitter.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet extends Entity {

  private String text;
  private Date date;
  private String ownerUsername;
  private List<String> usernamesOfUserWhoLikes = new ArrayList<>();

  public List<String> getUsernamesOfUserWhoLikes() {
    return usernamesOfUserWhoLikes;
  }

  public void setUsernamesOfUserWhoLikes(List<String> usernamesOfUserWhoLikes) {
    this.usernamesOfUserWhoLikes = usernamesOfUserWhoLikes;
  }

  public void addUsernameToLikes(String usernamesOfUserWhoLikes) {
    this.usernamesOfUserWhoLikes.add(usernamesOfUserWhoLikes);
  }

  public void removeUsernameFromLikes(String usernamesOfUserWhoLikes) {
    this.usernamesOfUserWhoLikes.remove(usernamesOfUserWhoLikes);
  }

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  public Tweet() {
    super();
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }


}

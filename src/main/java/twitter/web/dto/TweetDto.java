package twitter.web.dto;

import org.hibernate.validator.constraints.Length;
import twitter.beans.Tweet;
import twitter.beans.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Moluram on 4/13/2017.
 */
public class TweetDto {
  @NotNull
  @Length(min = 5, max = 400)
  private String text;
  private String ownerUsername;
  private String photoMin;
  private Date date;

  public TweetDto(Tweet tweet, User user) {
    this.ownerUsername = tweet.getOwnerUsername();
    this.date = tweet.getDate();
    this.text = tweet.getText();
    this.photoMin = user.getUserProfile().getMiniPhoto();
  }

  public TweetDto() {

  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getPhotoMin() {
    return photoMin;
  }

  public void setPhotoMin(String photoMin) {
    this.photoMin = photoMin;
  }

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}

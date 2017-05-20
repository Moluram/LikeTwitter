package twitter.web.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import twitter.entity.Tweet;
import twitter.entity.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Moluram on 4/13/2017.
 */
public class TweetDto {
  @NotNull
  @NotEmpty
  @Length(min = 5, max = 249)
  private String text;
  private String ownerUsername;
  private String photoMin;
  private Date date;
  private Integer numberOfLikes;
  private Long id;

  public TweetDto(Tweet tweet, User user) {
    this.ownerUsername = tweet.getOwnerUsername();
    this.date = tweet.getDate();
    this.text = tweet.getText();
    this.photoMin = user.getUserProfile().getMiniPhoto();
    this.numberOfLikes = tweet.getUsernamesOfUserWhoLikes().size();
    this.id = tweet.getId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TweetDto() {

  }

  public Integer getNumberOfLikes() {
    return numberOfLikes;
  }

  public void setNumberOfLikes(Integer numberOfLikes) {
    this.numberOfLikes = numberOfLikes;
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

package twitter.beans;

import java.util.Date;

/**
 * Created by Nikolay on 27.04.2017.
 */
public class Comment extends Entity {

  private Long tweetId;
  private User user;
  private Long parentCommentId;
  private String text;
  private Date date;
  private Integer depth;

  public Comment() {
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  public Long getTweetId() {
    return tweetId;
  }

  public void setTweetId(Long tweetId) {
    this.tweetId = tweetId;
  }

  public void setParentCommentId(Long parentCommentId) {
    this.parentCommentId = parentCommentId;
  }

  public Long getParentCommentId() {
    return parentCommentId;
  }

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", user=" + user +
        ", parentCommentId=" + parentCommentId +
        ", text='" + text + '\'' +
        ", date=" + date +
        '}';
  }

  public Integer getDepth() {
    return depth;
  }

  public void setDepth(Integer depth) {
    this.depth = depth;
  }
}

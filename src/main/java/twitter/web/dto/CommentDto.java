package twitter.web.dto;

/**
 * Created by Nikolay on 01.05.2017.
 */
public class CommentDto {
  private Long id;
  private Long parentId;
  private Long tweetId;
  private String text;
  private String author;
  private String authorPhoto;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Long getTweetId() {
    return tweetId;
  }

  public void setTweetId(Long tweetId) {
    this.tweetId = tweetId;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getAuthorPhoto() {
    return authorPhoto;
  }

  public void setAuthorPhoto(String authorPhoto) {
    this.authorPhoto = authorPhoto;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}

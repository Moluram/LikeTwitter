package twitter.web.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by Moluram on 4/13/2017.
 */
public class TweetDto {
  @NotNull
  @Length(min = 5, max = 400)
  private String text;

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}

package twitter.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import twitter.web.validators.ValidEmail;

/**
 * Created by moluram on 15.5.17.
 */
public class ContactDto {
  @NotEmpty
  private String name;

  @NotEmpty
  @ValidEmail
  private String email;

  @NotEmpty
  private String text;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}

package twitter.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import twitter.web.validators.PasswordMatches;

import javax.validation.constraints.NotNull;

/**
 * Created by Moluram on 3/29/2017.
 */
@PasswordMatches
public class PasswordDto {
  @NotNull
  @NotEmpty
  private String password;
  private String matchingPassword;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }
}

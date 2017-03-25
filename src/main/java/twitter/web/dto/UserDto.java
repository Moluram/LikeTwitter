package twitter.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import twitter.validators.PasswordMatches;
import twitter.validators.ValidEmail;

import javax.validation.constraints.NotNull;

/**
 * Represent a class to  transfer user from the view to the application
 * @author Aliaksei Chorny
 */
@PasswordMatches
public class UserDto {
  @NotNull
  @NotEmpty
  private String username;

  @NotNull
  @NotEmpty
  private String password;
  private String matchingPassword;

  @NotNull
  @NotEmpty
  @ValidEmail
  private String email;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

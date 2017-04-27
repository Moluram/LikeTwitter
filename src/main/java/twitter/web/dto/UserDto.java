package twitter.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import twitter.web.validators.PasswordMatches;
import twitter.web.validators.ValidEmail;

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
  private boolean enabled;

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

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

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isEnabled() {
    return enabled;
  }
}

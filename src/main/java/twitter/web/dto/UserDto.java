package twitter.web.dto;

import twitter.beans.User;

/**
 * Created by Moluram on 4/28/2017.
 */
public class UserDto {
  private String username;
  private String lastName;
  private String firstName;
  private String photoOriginal;
  private String photoMin;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPhotoOriginal() {
    return photoOriginal;
  }

  public void setPhotoOriginal(String photoOriginal) {
    this.photoOriginal = photoOriginal;
  }

  public String getPhotoMin() {
    return photoMin;
  }

  public void setPhotoMin(String photoMin) {
    this.photoMin = photoMin;
  }

  public UserDto(User user) {
    this.username = user.getUsername();
    this.firstName = user.getUserProfile().getFirstName();
    this.lastName  = user.getUserProfile().getLastName();
    this.photoMin = user.getUserProfile().getMiniPhoto();
    this.photoOriginal = user.getUserProfile().getPhotoUrl();
  }
}

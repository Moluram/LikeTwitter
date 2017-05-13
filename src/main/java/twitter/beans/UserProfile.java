package twitter.beans;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nikolay on 16.04.2017.
 */
public class UserProfile extends Entity {
  private String firstName="";
  private String lastName="";
  private String photoUrl= "initialdata/img/default.png";
  private String miniPhoto="default-mini.png";
  private List<String> links;
  private String status="";

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public List<String> getLinks() {
    return links;
  }

  public void setLinks(List<String> links) {
    this.links = links;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserProfile)) {
      return false;
    }

    UserProfile that = (UserProfile) o;

    if (getFirstName() != null ? !getFirstName().equals(that.getFirstName())
        : that.getFirstName() != null) {
      return false;
    }
    if (getLastName() != null ? !getLastName().equals(that.getLastName())
        : that.getLastName() != null) {
      return false;
    }
    if (getPhotoUrl() != null ? !getPhotoUrl().equals(that.getPhotoUrl())
        : that.getPhotoUrl() != null) {
      return false;
    }
    if (getLinks() != null ? !getLinks().equals(that.getLinks()) : that.getLinks() != null) {
      return false;
    }
    return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
  }

  @Override
  public int hashCode() {
    int result = getFirstName() != null ? getFirstName().hashCode() : 0;
    result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
    result = 31 * result + (getPhotoUrl() != null ? getPhotoUrl().hashCode() : 0);
    result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
    result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UserProfile{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", photoUrl='" + photoUrl + '\'' +
        ", links=" + links +
        ", status='" + status + '\'' +
        '}';
  }

  public String getMiniPhoto() {
    return miniPhoto;
  }

  public void setMiniPhoto(String miniPhoto) {
    this.miniPhoto = miniPhoto;
  }
}

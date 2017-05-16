package twitter.web.dto;

import twitter.beans.User;

/**
 * Created by Moluram on 4/28/2017.
 */
public class UserDto {
    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    private String photoOriginal;
    private String photoMin;
    private Boolean baned;
    private String role;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getUserProfile().getFirstName();
        this.lastName = user.getUserProfile().getLastName();
        this.photoMin = user.getUserProfile().getMiniPhoto();
        this.photoOriginal = user.getUserProfile().getPhotoUrl();
        this.baned=user.getBaned();
        this.role=user.getRole().getName();
    }

    public Boolean getBaned() {
        return baned;
    }

    public void setBaned(Boolean baned) {
        this.baned = baned;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

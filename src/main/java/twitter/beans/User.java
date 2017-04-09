package twitter.beans;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Represent a single user in the application
 */
@Entity
@Table(name = "USERS")
public class User {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "ENABLED")
  private Boolean enabled = false;

  @Column(name = "TOKENEXPIRED")
  private Boolean tokenExpired;

  private Role role;

  public Boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean isTokenExpired() {
    return tokenExpired;
  }

  public void setTokenExpired(Boolean tokenExpired) {
    this.tokenExpired = tokenExpired;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", enabled=" + enabled +
        ", tokenExpired=" + tokenExpired +
        ", role=" + role +
        '}';
  }
}

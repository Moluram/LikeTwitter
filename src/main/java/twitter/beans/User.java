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
  private boolean enabled;

  @Column(name = "TOKENEXPIRED")
  private boolean tokenExpired;

  @ManyToMany
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"))
  private Collection<Role> roles;

  public User(String username, String password, String email,
               Collection<Role> roles, boolean enabled) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.enabled = enabled;
    this.roles = roles;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isTokenExpired() {
    return tokenExpired;
  }

  public void setTokenExpired(boolean tokenExpired) {
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

  public Collection<Role> getRoles() {
    return roles;
  }
}

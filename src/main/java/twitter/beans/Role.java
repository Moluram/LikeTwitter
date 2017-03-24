package twitter.beans;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Represent a role for a user
 */
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "roles")
  private Collection<User> users;

  @ManyToMany
  @JoinTable(
      name = "roles_privileges",
      joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
  private Collection<Privilege> privileges;

  public Role(String name, List<Privilege> privileges) {
    this.name = name;
    this.privileges = privileges;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<Privilege> getPrivileges() {
    return privileges;
  }
}

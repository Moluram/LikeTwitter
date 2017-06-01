package twitter.entity;

import java.util.Collection;
import java.util.List;

/**
 * Represent a role for a user
 */
public class Role extends Entity {

  private String name;

  private Collection<Privilege> privileges;

  public Role() {
  }

  public Role(String name, List<Privilege> privileges) {
    this.name = name;
    this.privileges = privileges;
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

  public void setPrivileges(Collection<Privilege> privileges) {
    this.privileges = privileges;
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", privileges=" + privileges +
        '}';
  }
}

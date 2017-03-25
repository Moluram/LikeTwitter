package twitter.service.role;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Role;

import java.util.List;

/**
 * Service serve for give access to the privileges
 */
@Service
@Scope("singleton")
public interface RoleService {
  /**
   * Adds role to the app
   * @param role - role to add
   */
  void addRole(Role role);

  /**
   * Returns list of Role's from this app
   * @return List<Role> - list of Role's
   */
  List<Role> listRole();

  /**
   * Removes role from app
   * @param id - role id
   */
  void removeRole(Integer id);

  Role findByName(String name);
}

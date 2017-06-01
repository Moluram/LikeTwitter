package twitter.service.role;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.entity.Role;

import java.util.List;

/**
 * Service serve for give access to the privileges
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
  void removeRole(Long id);

  Role findByName(String name);
}

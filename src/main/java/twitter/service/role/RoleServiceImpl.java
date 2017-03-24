package twitter.service.role;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Role;

import java.util.List;

/**
 * Created by moluram on 24.3.17.
 */
@Service("roleService")
@Scope("singleton")
public class RoleServiceImpl implements RoleService {
  public void addRole(Role role) {

  }

  public List<Role> listRole() {
    return null;
  }

  public void removeRole(Integer id) {

  }

  public Role findByName(String name) {
    return null;
  }
}

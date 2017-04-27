package twitter.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Role;
import twitter.dao.IRoleDAO;

import java.util.List;

@Service("roleService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RoleServiceImpl implements RoleService {
  private IRoleDAO roleDAO;

  @Autowired
  public void setRoleDAO(IRoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

  public void addRole(Role role) {
    roleDAO.create(role);
  }

  public List<Role> listRole() {
    return roleDAO.getAll();
  }

  public void removeRole(Long id) {
    roleDAO.delete(id);
  }

  public Role findByName(String name) {
    return roleDAO.findByName(name);
  }
}

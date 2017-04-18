package twitter.service.privilage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Privilege;
import twitter.dao.privilege.PrivilegeDAO;

import java.util.List;


@Service("privilegeService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrivilegeServiceImpl implements PrivilegeService {
  private PrivilegeDAO privilegeDAO;

  @Autowired
  public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
    this.privilegeDAO = privilegeDAO;
  }

  public void addPrivilege(Privilege privilege) {
    privilegeDAO.create(privilege);
  }

  public List<Privilege> listPrivilege() {
    return privilegeDAO.getAll();
  }

  public void removePrivilege(Long id) {
    privilegeDAO.delete(id);
  }

  public Privilege findByName(String name) {
    return privilegeDAO.findByName(name);
  }


}

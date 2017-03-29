package twitter.service.privilage;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Privilege;

import java.util.List;


@Service("privilegeService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrivilegeServiceImpl implements PrivilegeService {
  public void addPrivilege(Privilege privilege) {

  }

  public List<Privilege> listPrivilege() {
    return null;
  }

  public void removePrivilege(Integer id) {

  }

  public Privilege findByName(String name) {
    return null;
  }


}

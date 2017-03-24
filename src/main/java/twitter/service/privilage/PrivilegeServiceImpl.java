package twitter.service.privilage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Privilege;

import java.util.List;

/**
 * Created by moluram on 24.3.17.
 */
@Service("privilegeService")
@Scope("singleton")
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

package twitter.service.privilage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Privilege;

import java.util.List;

/**
 * Created by moluram on 24.3.17.
 */
@Service
@Scope("singleton")
public interface PrivilegeService {
  /**
   * Adds user to the app
   * @param privilege - Privilege to add
   */
  void addPrivilege(Privilege privilege);

  /**
   * Returns list of Privilege's from this app
   * @return List<Privilege> - list of Privilege's
   */
  List<Privilege> listPrivilege();

  /**
   * Removes privilege from app
   * @param id - privilege id
   */
  void removePrivilege(Integer id);

  Privilege findByName(String name);
}

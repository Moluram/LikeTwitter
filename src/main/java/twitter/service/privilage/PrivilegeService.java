package twitter.service.privilage;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.entity.Privilege;

import java.util.List;

/**
 * Service serve for give access to the privileges
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
  void removePrivilege(Long id);

  Privilege findByName(String name);
}

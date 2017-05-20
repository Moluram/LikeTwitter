package twitter.dao;

import java.util.Collection;
import java.util.List;
import twitter.entity.Privilege;

/**
 * Created by Nikolay on 06.04.2017.
 */
public interface IPrivilegeDAO {
  /**
   * Adds privilege to the database
   * @param privilege - privilege to add
   */
  Long create(Privilege privilege);

  /**
   * Read privilege from the database
   * @param id - privilege's id for read
   */
  Privilege read(Long id);

  /**
   * Update privilege in the database
   * @param privilege - privilege for update
   */
  void update(Privilege privilege);

  /**
   * Removes privilege from db
   * @param id - privilege's id for delete
   */
  void delete(Long id);

  /**
   * Returns list of privilege's from this database
   * @return List<privilege> - list of privilege's
   */
  List<Privilege> getAll();

  /**
   * Returns role from this database by name
   * @param name - role's name for return
   */
  Privilege findByName(String name);

  Collection<Privilege> getPrivilegesByRoleId(Long roleId);
}

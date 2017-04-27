package twitter.dao;

import java.util.Collection;
import java.util.List;
import twitter.beans.Role;

/**
 * Created by Nikolay on 05.04.2017.
 */
public interface IRoleDAO {
  /**
   * Adds pole to the database
   * @param role - role to add
   */
  Long create(Role role);

  /**
   * Read role from the database
   * @param id - role's id for read
   */
  Role read(Long id);

  /**
   * Update role in the database
   * @param role - role for update
   */
  void update(Role role);

  /**
   * Removes role from db
   * @param id - role's id for delete
   */
  void delete(Long id);

  /**
   * Returns list of role's from this database
   * @return List<Role> - list of role's
   */
  List<Role> getAll();

  /**
   * Returns role from this database by name
   * @param name - role's name for return
   */
  Role findByName(String name);
}

package twitter.dao.user;

import twitter.beans.User;

import java.util.List;

/**
 * Represents an interface to work with database
 * @author Aliaksei Chorny
 */
public interface UserDAO {
  /**
   * Adds user to the database
   * @param user - user to add
   */
  Integer create(User user);

  /**
   * Read user from the database
   * @param id - user's id for read
   */
  User read(Integer id);

  /**
   * Update user in the database
   * @param user - user for update
   */
  void update(User user);

  /**
   * Removes user from db
   * @param id - user's id for delete
   */
  void delete(Integer id);

  /**
   * Returns list of User's from this database
   * @return List<User> - list of User's
   */
  List<User> getAll();
}

package twitter.dao;

import twitter.beans.User;

import java.util.List;

/**
 * Represents an interface to work with database
 */
public interface UserDAO {
  /**
   * Adds user to the database
   * @param user - user to add
   */
  void addUser(User user);

  /**
   * Returns list of User's from this database
   * @return List<User> - list of User's
   */
  List<User> listUser();

  /**
   * Removes user from db
   * @param id - user id
   */
  void removeUser(Integer id);
}

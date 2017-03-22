package twitter.sevice;

import twitter.beans.User;

import java.util.List;

/**
 * Represents an interface to work with users in app
 * @author Aliaksei Chorny
 */
public interface UserService {
  /**
   * Adds user to the app
   * @param user - user to add
   */
  void addUser(User user);

  /**
   * Returns list of User's from this app
   * @return List<User> - list of User's
   */
  List<User> listUser();

  /**
   * Removes user from app
   * @param id - user id
   */
  void removeUser(Integer id);
}

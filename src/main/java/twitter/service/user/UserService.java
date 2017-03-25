package twitter.service.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Privilege;
import twitter.beans.User;
import twitter.web.dto.UserDto;

import java.util.List;

/**
 * Represents an interface to work with users in app
 * @author Aliaksei Chorny
 */
@Service
@Scope("singleton")
public interface UserService {
  /**
   * Adds user to the app
   * @param user - user to add
   */
  void addUser(User user);

  /**
   * Returns list of User's from this app (I really don't like the idea of
   * getting all user's as one list)
   * @return List<User> - list of User's
   */
  List<User> listUser();

  /**
   * Removes user from app
   * @param id - user id
   */
  void removeUser(Integer id);

  User findByName(String name);

  User registerNewUserAccount(UserDto accountDto);
}

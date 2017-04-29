package twitter.service.user;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.dao.exception.DAOException;
import twitter.web.dto.UserDto;

import java.util.List;
import twitter.web.exceptions.EmailExistsException;
import twitter.web.exceptions.UsernameExistsException;

/**
 * Represents an interface to work with users in app
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
  void removeUser(Long id);

  @Transactional
  User registerNewUserAccount(UserDto accountDto)
          throws UsernameExistsException, EmailExistsException;

  void createVerificationToken(User user, String token);

  VerificationToken getVerificationToken(String token);

  void saveRegisteredUser(User user);

  VerificationToken generateNewVerificationToken(String existingToken);

  User getUserByToken(String token);

  User getUserByUsername(String username);

  void createPasswordResetTokenForUser(User user, String token);

  void changeUserPassword(User user, String password);

  User findByEmail(String email);

  void updateUserPhoto(User user,String photo);

  List<String> getUsernamesStartsWith(String username, Integer maxSuggestions);
}
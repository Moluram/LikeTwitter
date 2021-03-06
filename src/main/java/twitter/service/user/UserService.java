package twitter.service.user;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter.entity.User;
import twitter.entity.VerificationToken;
import twitter.web.dto.SignUpDto;
import twitter.web.exceptions.EmailExistsException;
import twitter.web.exceptions.UsernameExistsException;

import java.util.List;

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


  List<User> listUser(Long limit, Long offset);

  /**
   * Removes user from app
   * @param id - user id
   */
  void removeUser(Long id);

  @Transactional
  User registerNewUserAccount(SignUpDto accountDto)
          throws UsernameExistsException, EmailExistsException;

  VerificationToken createVerificationToken(User user, String token);

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

  List<String> getUsernamesContains(String username, Integer maxSuggestions);

  Long count();

  Long count(String attr,String value);

  User getById(Long id);

  void updateUserBan(Long id,Boolean newValue);
}
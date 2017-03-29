package twitter.service.user;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.web.dto.UserDto;

import java.util.List;

/**
 * Service serve for give access to the privileges
 */
@Service("userService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceImpl implements UserService{
  public void addUser(User user) {

  }

  public List<User> listUser() {
    return null;
  }

  public void removeUser(Integer id) {

  }

  public User findByName(String name) {
    return null;
  }

  @Override
  public User registerNewUserAccount(UserDto accountDto) {
    User user = new User();
    user.setEmail(accountDto.getEmail());
    user.setPassword(accountDto.getPassword());
    user.setUsername(accountDto.getUsername());
    return user;
  }

  @Override
  public void createVerificationToken(User user, String token) {
    // new VerificationToken(user, token, VerificationToken.EXPIRATION);
  }

  @Override
  public VerificationToken getVerificationToken(String token) {
    return null; // null if not found pls
  }

  @Override
  public void saveRegisteredUser(User user) {

  }

  @Override
  public VerificationToken generateNewVerificationToken(String existingToken) {
    return null;
  }

  @Override
  public User getUserByToken(String token) {
    return null;
  }

  @Override
  public User getUserByUsername(String username) {
    return null;
  }

  @Override
  public void createPasswordResetTokenForUser(User user, String token) {

  }

  @Override
  public void changeUserPassword(User user, String password) {

  }
}

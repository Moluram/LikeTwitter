package twitter.service.user;

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
@Scope("singleton")
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
    return null;
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
}

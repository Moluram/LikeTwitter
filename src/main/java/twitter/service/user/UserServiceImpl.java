package twitter.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import twitter.beans.*;
import twitter.web.dto.UserDto;
import twitter.web.exceptions.EmailExistsException;
import twitter.web.exceptions.UsernameExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service serve for give access to the privileges
 */
@Service("userService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserServiceImpl implements UserService{
  private List<User> userList = new ArrayList<>();
  private List<VerificationToken> tokens = new ArrayList<>();
  private List<PasswordResetToken> passwordResetTokens = new ArrayList<>();

  public void addUser(User user) {
      userList.add(user);
  }

  public List<User> listUser() {
    return null;
  }

  public void removeUser(Integer id) {

  }

  public User findByName(String name) {
    for (User user: userList) {
      if (user.getUsername().equals(name)) {
        return user;
      }
    }
    return null;
  }

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  public User registerNewUserAccount(UserDto accountDto) {
    for (User user: userList) {
      if (user.getUsername().equals(accountDto.getUsername())){
        throw new UsernameExistsException();
      }
      if (user.getEmail().equals(accountDto.getEmail())) {
        throw new EmailExistsException();
      }
    }
    User user = new User();
    user.setEmail(accountDto.getEmail());
    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
    user.setUsername(accountDto.getUsername());
    List<Privilege> privileges = new ArrayList<>();
    privileges.add(new Privilege("VIEW_PAGES"));
    user.setRole(new Role("USER", privileges));
    userList.add(user);
    return user;
  }

  @Override
  public void createVerificationToken(User user, String token) {
    tokens.add(new VerificationToken(user, token, VerificationToken.EXPIRATION));
  }

  @Override
  public VerificationToken getVerificationToken(String token) {
    for (VerificationToken verificationToken: tokens) {
      if (verificationToken.getToken().equals(token)) {
        return verificationToken;
      }
    }
    return null; // null if not found pls
  }

  @Override
  public void saveRegisteredUser(User user) {

  }

  @Override
  public VerificationToken generateNewVerificationToken(String existingToken) {
    for (VerificationToken verificationToken: tokens) {
      if (verificationToken.getToken().equals(existingToken)) {
        verificationToken.setToken(UUID.randomUUID().toString());
        return verificationToken;
      }
    }
    return null; // null if not found pls
  }

  @Override
  public User getUserByToken(String token) {
    for (VerificationToken verificationToken: tokens) {
      if (verificationToken.getToken().equals(token)) {
        return verificationToken.getUser();
      }
    }
    return null; // null if not found pls
  }

  @Override
  public User getUserByUsername(String username) {
    for (User user: userList) {
      if (user.getUsername().equals(username)){
        return user;
      }
    }
    return null;
  }

  @Override
  public void createPasswordResetTokenForUser(User user, String token) {
    passwordResetTokens.add(new PasswordResetToken(user, token, PasswordResetToken.EXPIRATION));
  }

  @Override
  public void changeUserPassword(User user, String password) {
    for (User iUser: userList) {
      if (iUser.equals(user)){
        iUser.setPassword(password);
      }
    }
  }

  @Override
  public User findByEmail(String email) {
    for (User user: userList) {
      if (user.getEmail().equals(email)){
        return user;
      }
    }
    return null;
  }
}

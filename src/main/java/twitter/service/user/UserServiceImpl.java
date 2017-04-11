package twitter.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import twitter.beans.*;
import twitter.dao.passwordresetdao.PasswordResetRepository;
import twitter.dao.role.RoleDAO;
import twitter.dao.user.UserDAO;
import twitter.dao.verificationtoken.VerificationTokenDAO;
import twitter.web.dto.UserDto;
import twitter.web.exceptions.EmailExistsException;
import twitter.web.exceptions.UsernameExistsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Service serve for give access to the privileges
 */
@Service("userService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserServiceImpl implements UserService {
  private static final String USER_ROLE = "USER";

  private UserDAO userDAO;
  private RoleDAO roleDAO;
  private VerificationTokenDAO verificationTokenDAO;
  private PasswordResetRepository passwordResetRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public void setPasswordResetRepository(PasswordResetRepository passwordResetRepository) {
    this.passwordResetRepository = passwordResetRepository;
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Autowired
  public void setRoleDAO(RoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

  @Autowired
  public void setVerificationTokenDAO(VerificationTokenDAO verificationTokenDAO){
    this.verificationTokenDAO = verificationTokenDAO;
  }

  public void addUser(User user) {
      userDAO.create(user);
  }

  @Override
  public User getUserByUsername(String username) {
    return userDAO.findByUsername(username);
  }

  @Override
  public User findByEmail(String email) {
    return userDAO.findByEmail(email);
  }

  public void removeUser(Integer id) {
    userDAO.delete(id);
  }

  public List<User> listUser() {
    return userDAO.getAll();
  }

  @Override
  public User registerNewUserAccount(UserDto accountDto) {
    if (null != userDAO.findByUsername(accountDto.getUsername())) {
      throw new UsernameExistsException();
    }
    if (null != userDAO.findByEmail(accountDto.getEmail())) {
      throw new EmailExistsException();
    }
    User user = new User();
    user.setEmail(accountDto.getEmail());
    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
    user.setUsername(accountDto.getUsername());
    user.setRole(roleDAO.findByName(USER_ROLE));
    addUser(user);
    return user;
  }

  @Override
  public void createVerificationToken(User user, String token) {
    verificationTokenDAO.create(new VerificationToken(user, token, VerificationToken.EXPIRATION));
  }

  @Override
  public VerificationToken getVerificationToken(String token) {
    return verificationTokenDAO.findByTokenName(token); // null if not found pls
  }

  @Override
  public void saveRegisteredUser(User user) {
    userDAO.update(user);
  }

  @Override
  public VerificationToken generateNewVerificationToken(String existingToken) {
    VerificationToken token = verificationTokenDAO.findByTokenName(existingToken);
    if (token == null) {
      return token;
    }
    token.setToken(UUID.randomUUID().toString());
    return token; // null if not found pls
  }

  @Override
  public User getUserByToken(String token) {
    return verificationTokenDAO.findByTokenName(token).getUser(); // null if not found pls
  }

  @Override
  public void createPasswordResetTokenForUser(User user, String token) {
    passwordResetRepository.create(
        new PasswordResetToken(user, token, PasswordResetToken.EXPIRATION));
  }

  @Override
  public void changeUserPassword(User user, String password) {
    user.setPassword(password);
    userDAO.update(user);
  }
}

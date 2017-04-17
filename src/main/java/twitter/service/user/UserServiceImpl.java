package twitter.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import twitter.beans.*;
import twitter.constants.RolesAndPrivileges;
import twitter.dao.UserProfileDAO;
import twitter.dao.exception.DAOException;
import twitter.dao.passwordresetdao.PasswordResetRepository;
import twitter.dao.role.RoleDAO;
import twitter.dao.UserDAO;
import twitter.dao.verificationtoken.VerificationTokenDAO;
import twitter.web.dto.UserDto;
import twitter.web.exceptions.EmailExistsException;
import twitter.web.exceptions.UsernameExistsException;

import java.util.List;
import java.util.UUID;

/**
 * Service serve for give access to the privileges
 */
@Service("userService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserServiceImpl implements UserService {

  private UserDAO userDAO;
  private RoleDAO roleDAO;
  private UserProfileDAO userProfileDAO;
  private VerificationTokenDAO verificationTokenDAO;
  private PasswordResetRepository passwordResetRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public void setUserProfileDAO(UserProfileDAO userProfileDAO) {
    this.userProfileDAO = userProfileDAO;
  }

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

  //TODO: catch exception
  @Override
  public User getUserByUsername(String username){
    User user=null;
    try {
      user = userDAO.findByUsername(username);
    } catch (DAOException e) {
      e.printStackTrace();
    }
    return user;
  }

  //TODO: catch exception
  @Override
  public User findByEmail(String email){
    User user=null;
    try {
      user = userDAO.findByEmail(email);
    } catch (DAOException e) {
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public void updateUserPhoto(User user, String photo) {
    UserProfile userProfile=user.getUserProfile();
    userProfile.setPhotoUrl(photo);
    userProfileDAO.update(userProfile);
  }

  public void removeUser(Long id) {
    userDAO.delete(id);
  }

  public List<User> listUser() {
    return userDAO.getAll();
  }

  //TODO: catch exception
  @Override
  public User registerNewUserAccount(UserDto accountDto){
    try {
      if (null != userDAO.findByUsername(accountDto.getUsername())) {
        throw new UsernameExistsException();
      }
    } catch (DAOException e) {
      e.printStackTrace();
    }
    try {
      if (null != userDAO.findByEmail(accountDto.getEmail())) {
        throw new EmailExistsException();
      }
    } catch (DAOException e) {
      e.printStackTrace();
    }
    UserProfile userProfile=new UserProfile();

    System.out.println(userProfile.getPhotoUrl());

    userProfileDAO.create(userProfile);
    User user = new User();
    user.setUserProfile(userProfile);
    user.setEmail(accountDto.getEmail());
    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
    user.setUsername(accountDto.getUsername());
    user.setRole(roleDAO.findByName(RolesAndPrivileges.ROLE_USER));
    addUser(user);
    return user;
  }

  @Override
  public void createVerificationToken(User user, String token) {
    verificationTokenDAO.create(new VerificationToken(user, token, VerificationToken.EXPIRATION));
  }

  @Override
  public VerificationToken getVerificationToken(String token) {
    VerificationToken vToken=null;
    try {
      vToken =  verificationTokenDAO.findByTokenName(token); // null if not found pls
    } catch (DAOException e) {
      e.printStackTrace();
    }
    return vToken;
  }

  @Override
  public void saveRegisteredUser(User user) {
    userDAO.update(user);
  }

  @Override
  public VerificationToken generateNewVerificationToken(String existingToken) {
    VerificationToken token = null;
    try {
      token = verificationTokenDAO.findByTokenName(existingToken);
    } catch (DAOException e) {
      e.printStackTrace();
    }
    if (token == null) {
      return token;
    }
    token.setToken(UUID.randomUUID().toString());
    return token; // null if not found pls
  }

  @Override
  public User getUserByToken(String token) {
    User user=null;
    try {
      user =  verificationTokenDAO.findByTokenName(token).getUser(); // null if not found pls
    } catch (DAOException e) {
      e.printStackTrace();
    }
    return  user;
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

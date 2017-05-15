package twitter.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import twitter.beans.PasswordResetToken;
import twitter.beans.User;
import twitter.beans.UserProfile;
import twitter.beans.VerificationToken;
import twitter.constants.RolesAndPrivileges;
import twitter.dao.*;
import twitter.service.image.ImageService;
import twitter.web.dto.SignUpDto;
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
public class UserServiceImpl implements UserService {

  private IUserDAO userDAO;
  private IRoleDAO roleDAO;
  private IUserProfileDAO userProfileDAO;
  private IVerificationTokenDAO verificationTokenDAO;
  private IPasswordResetDAO passwordResetRepository;
  private PasswordEncoder passwordEncoder;
  private ImageService imageService;

  @Autowired
  public void setImageService(ImageService imageService) {
    this.imageService = imageService;
  }

  @Autowired
  public void setUserProfileDAO(IUserProfileDAO userProfileDAO) {
    this.userProfileDAO = userProfileDAO;
  }

  @Autowired
  public void setPasswordResetRepository(IPasswordResetDAO passwordResetRepository) {
    this.passwordResetRepository = passwordResetRepository;
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void setUserDAO(IUserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Autowired
  public void setRoleDAO(IRoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

  @Autowired
  public void setVerificationTokenDAO(IVerificationTokenDAO verificationTokenDAO) {
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

  @Override
  public void updateUserPhoto(User user, String photo) {
    UserProfile userProfile = user.getUserProfile();
    userProfile.setPhotoUrl(photo);
    userProfileDAO.update(userProfile);
  }

  @Override
  public List<String> getUsernamesStartsWith(String username, Integer maxSuggestions) {
    List<String> list = new ArrayList<>();
    List<User> users = userDAO.getAll();
    int counter = 0;
    for (User user: users) {
      if (user.getUsername().startsWith(username)) {
        list.add(user.getUsername());
        counter++;
      }
      if (counter >= maxSuggestions) {
        break;
      }
    }
    return list;
  }

  @Override
  public List<String> getUsernamesContains(String username, Integer maxSuggestions) {
    List<String> list = new ArrayList<>();
    List<User> users = userDAO.getAll();
    int counter = 0;
    for (User user: users) {
      if (user.getUsername().contains(username)) {
        list.add(user.getUsername());
        counter++;
      }
      if (counter >= maxSuggestions) {
        break;
      }
    }
    return list;
  }

  public void removeUser(Long id) {
    userDAO.delete(id);
  }

  public List<User> listUser() {
    return userDAO.getAll();
  }

  @Override
  public User registerNewUserAccount(SignUpDto accountDto) {
    if (null != userDAO.findByUsername(accountDto.getUsername())) {
      throw new UsernameExistsException();
    }
    if (null != userDAO.findByEmail(accountDto.getEmail())) {
      throw new EmailExistsException();
    }
    UserProfile userProfile = new UserProfile();
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
  public VerificationToken createVerificationToken(User user, String token) {
    VerificationToken verificationToken = new VerificationToken(user, token, VerificationToken
        .EXPIRATION);
    verificationTokenDAO.create(verificationToken);
    return verificationToken;
  }

  @Override
  public VerificationToken getVerificationToken(String token) {
    return verificationTokenDAO.findByTokenName(token);
  }

  @Override
  public void saveRegisteredUser(User user) {
    userDAO.update(user);
  }

  @Override
  public VerificationToken generateNewVerificationToken(String existingToken) {
    VerificationToken token = verificationTokenDAO.findByTokenName(existingToken);
    if (token == null) {
      return null;
    }
    token.setToken(UUID.randomUUID().toString());
    return token;
  }

  @Override
  public User getUserByToken(String token) {
    return verificationTokenDAO.findByTokenName(token).getUser();
  }

  @Override
  public void createPasswordResetTokenForUser(User user, String token) {
    passwordResetRepository.create(
            new PasswordResetToken(user, token, PasswordResetToken.EXPIRATION));
  }

  @Override
  public void changeUserPassword(User user, String password) {
    user.setPassword(passwordEncoder.encode(password));
    userDAO.update(user);
  }
}
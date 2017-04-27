package twitter.service.userprofile;

import java.util.List;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.UserProfile;
import twitter.dao.IUserProfileDAO;

/**
 * Created by Nikolay on 26.04.2017.
 */
@Service("userProfileService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserProfileServiceImpl implements UserProfileService {

  private final IUserProfileDAO userProfileDAO;

  public UserProfileServiceImpl(IUserProfileDAO userProfileDAO) {
    this.userProfileDAO = userProfileDAO;
  }

  @Override
  public void addUserProfile(UserProfile userProfile) {
    userProfileDAO.create(userProfile);
  }

  @Override
  public void updateUserProfile(UserProfile userProfile) {
    userProfileDAO.update(userProfile);
  }

  @Override
  public List<UserProfile> listUserProfile() {
    return userProfileDAO.getAll();
  }

  @Override
  public void removeUserProfile(Long id) {
    userProfileDAO.delete(id);
  }
}

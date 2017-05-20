package twitter.service.userprofile;

import java.util.List;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.entity.UserProfile;

/**
 * Created by Nikolay on 26.04.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface UserProfileService {

  void addUserProfile(UserProfile userProfile);

  void updateUserProfile(UserProfile userProfile);

  List<UserProfile> listUserProfile();

  void removeUserProfile(Long id);

}

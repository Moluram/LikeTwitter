package twitter.service.userprofile;

import twitter.entity.UserProfile;

import java.util.List;

/**
 * Created by Nikolay on 26.04.2017.
 */
public interface UserProfileService {

  void addUserProfile(UserProfile userProfile);

  void updateUserProfile(UserProfile userProfile);

  List<UserProfile> listUserProfile();

  void removeUserProfile(Long id);

}

package twitter.service.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import twitter.beans.User;
import twitter.beans.UserProfile;

/**
 * Created by Nikolay on 24.04.2017.
 */
@Service
public interface ImageService {
  void storeImage(MultipartFile file, UserProfile userProfile);
}

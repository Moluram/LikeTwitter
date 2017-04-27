package twitter.service.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import twitter.beans.UserProfile;
import twitter.service.storage.FileNamingService;
import twitter.service.storage.StorageService;
import twitter.service.userprofile.UserProfileService;

/**
 * Created by Nikolay on 24.04.2017.
 */
@Service
public class FileSystemImageService implements ImageService {

  private final StorageService storageService;
  private final FileNamingService fileNamingService;
  private final UserProfileService userProfileService;

  private static final Integer IMG_WIDTH=200;
  private static final Integer IMG_HEIGHT=200;

  @Autowired
  public FileSystemImageService(StorageService storageService,
      FileNamingService fileNamingService, UserProfileService userProfileService) {
    this.storageService = storageService;
    this.fileNamingService = fileNamingService;
    this.userProfileService = userProfileService;
  }

  @Override
  public void storeImage(MultipartFile file, UserProfile userProfile) {
    String originalName = file.getOriginalFilename();
    BufferedImage originalImage=convertToImage(file);
    BufferedImage resizedImage= Scalr.resize(originalImage,IMG_WIDTH,IMG_HEIGHT);
    String newNameOriginal=fileNamingService.generateNewFileName(originalName);
    userProfile.setPhotoUrl(newNameOriginal);
    String newNameMini=fileNamingService.generateNewFileName(originalName);
    userProfile.setMiniPhoto(newNameMini);
    storageService.storeImage(originalImage,newNameOriginal);
    storageService.storeImage(resizedImage,newNameMini);
    userProfileService.updateUserProfile(userProfile);
  }

  private BufferedImage convertToImage(MultipartFile file) {
    BufferedImage image;
    try {
      image = ImageIO.read(file.getInputStream());
    } catch (IOException e) {
      throw new ImageException("Can't convert MultipartFile to BuffereImage!", e);
    }
    if (image == null) {
      throw new ImageException("Can't convert MultipartFile to BuffereImage: returned null!");
    }
    return image;
  }
}

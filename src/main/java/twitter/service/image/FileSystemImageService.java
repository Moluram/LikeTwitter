package twitter.service.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import twitter.beans.UserProfile;
import twitter.service.storage.FileNamingService;
import twitter.service.storage.StorageService;
import twitter.service.userprofile.UserProfileService;

import static twitter.constants.InitialPhotoSettings.MINI_IMG_HEIGHT;
import static twitter.constants.InitialPhotoSettings.MINI_IMG_WIDTH;

/**
 * Created by Nikolay on 24.04.2017.
 */
@Service
public class FileSystemImageService implements ImageService {

  private final StorageService storageService;
  private final FileNamingService fileNamingService;
  private final UserProfileService userProfileService;


  @Autowired
  public FileSystemImageService(StorageService storageService,
      FileNamingService fileNamingService, UserProfileService userProfileService) {
    this.storageService = storageService;
    this.fileNamingService = fileNamingService;
    this.userProfileService = userProfileService;
  }

  @Override
  public void storeImage(MultipartFile file,UserProfile userProfile){
    String originalName = file.getOriginalFilename();
    String newNameOriginal=fileNamingService.generateNewFileName(originalName);
    userProfile.setPhotoUrl(newNameOriginal);
    String newNameMini=fileNamingService.generateNewFileName(originalName);
    userProfile.setMiniPhoto(newNameMini);
    storeOriginalImage(file,newNameOriginal);
    storeOriginalImage(file,newNameMini);
    userProfileService.updateUserProfile(userProfile);
  }

  @Override
  public void storeOriginalImage(MultipartFile file, String name) {
    BufferedImage img=convertToImage(file);
    storageService.storeImage(img,name);
  }

  @Override
  public void storeResizedImage(MultipartFile file,String name,Integer width,Integer height){
    BufferedImage originalImage=convertToImage(file);
    BufferedImage resizedImage= Scalr.resize(originalImage,width,height);
    storageService.storeImage(resizedImage,name);
  }

  private BufferedImage convertToImage(MultipartFile file) {
    BufferedImage image;
    try {
      image = ImageIO.read(file.getInputStream());
    } catch (IOException e) {
      throw new ImageException("Can't convert MultipartFile to BufferedImage!", e);
    }
    if (image == null) {
      throw new ImageException("Can't convert MultipartFile to BufferedImage: returned null!");
    }
    return image;
  }
}

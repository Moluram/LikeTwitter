package twitter.service.image;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import twitter.service.storage.StorageProperties;

/**
 * Created by Nikolay on 17.04.2017.
 */
@Service
public class ImageNamingService {
  private final Path rootLocation;

  @Autowired
  public ImageNamingService(StorageProperties properties) {
    this.rootLocation = Paths.get(properties.getLocation());
  }

  public String generateNewFileName(String originalName,String username){
    return genarateUniqueFileName(username)+"."+getFileFormat(originalName);
  }

  public String generateNewFileNameForMini(String originalName,String username){
    return genarateUniqueFileName(username)+"-mini."+getFileFormat(originalName);
  }

  public String getFileFormat(String fileName){
    String[] parts= fileName.split("\\.");
    return parts[parts.length-1];
  }

  private String genarateUniqueFileName(String username) {
    String filename= username+ Calendar.getInstance().getTimeInMillis();
    if (Files.notExists(rootLocation.resolve(filename))){
      return filename;
    }
    return genarateUniqueFileName(username);
  }
}

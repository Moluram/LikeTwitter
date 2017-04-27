package twitter.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Nikolay on 17.04.2017.
 */
@Service
public class FileNamingService {
  private final Path rootLocation;

  @Autowired
  public FileNamingService(StorageProperties properties) {
    this.rootLocation = Paths.get(properties.getLocation());
  }

  public String generateNewFileName(String originalName){
    return genarateUniqueFileName()+"."+getFileFormat(originalName);
  }

  public String getFileFormat(String fileName){
    String[] parts= fileName.split("\\.");
    return parts[parts.length-1];
  }

  private String genarateUniqueFileName() {
    String filename= UUID.randomUUID().toString();
    if (Files.notExists(rootLocation.resolve(filename))){
      return filename;
    }
    return genarateUniqueFileName();
  }
}

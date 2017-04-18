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

  public String generateNewFileName(MultipartFile file){
    String originalName=file.getOriginalFilename();
    String[] parts= originalName.split("\\.");
    String newName=genarateUniqueFileName()+"."+parts[parts.length-1];
    return newName;
  }

  public String genarateUniqueFileName() {
    String filename= UUID.randomUUID().toString();
    if (Files.notExists(rootLocation.resolve(filename))){
      return filename;
    }
    return genarateUniqueFileName();
  }
}

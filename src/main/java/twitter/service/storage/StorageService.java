package twitter.service.storage;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Nikolay on 16.04.2017.
 */
public interface StorageService {

  void init();

  void store(MultipartFile file,String filename);

  void storeImage(BufferedImage image,String filename);

  Stream<Path> loadAll();

  Path load(String filename);

  Resource loadAsResource(String filename);

  void deleteAll();

}

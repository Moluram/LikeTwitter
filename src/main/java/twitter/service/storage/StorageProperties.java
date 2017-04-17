package twitter.service.storage;

import org.springframework.stereotype.Component;

/**
 * Created by Nikolay on 16.04.2017.
 */
@Component
public class StorageProperties {

  /**
   * Folder location for storing files
   */
  private String location = "upload-dir";

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

}

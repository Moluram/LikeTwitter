package twitter.service.storage;

/**
 * Created by Nikolay on 16.04.2017.
 */
public class StorageException extends RuntimeException {

  public StorageException(String message) {
    super(message);
  }

  public StorageException(String message, Throwable cause) {
    super(message, cause);
  }
}

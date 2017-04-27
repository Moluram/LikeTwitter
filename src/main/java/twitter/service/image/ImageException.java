package twitter.service.image;

/**
 * Created by Nikolay on 24.04.2017.
 */
public class ImageException extends RuntimeException {

  public ImageException() {
  }

  public ImageException(String message) {
    super(message);
  }

  public ImageException(String message, Throwable cause) {
    super(message, cause);
  }

  public ImageException(Throwable cause) {
    super(cause);
  }

  public ImageException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

package twitter.dao.PasswordResetDao;

import org.springframework.stereotype.Component;
import twitter.beans.PasswordResetToken;

/**
 * Created by Moluram on 3/29/2017.
 */
@Component
public class PasswordResetRepositoryImpl implements PasswordResetRepository {
  @Override
  public PasswordResetToken findByToken(String token) {
    return null;
  }
}

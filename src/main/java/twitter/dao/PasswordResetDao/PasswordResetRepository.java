package twitter.dao.PasswordResetDao;

import org.springframework.stereotype.Component;
import twitter.beans.PasswordResetToken;

/**
 * Created by Moluram on 3/29/2017.
 */
@Component
public interface PasswordResetRepository {
  PasswordResetToken findByToken(String token);
}

package twitter.dao.passwordresetdao;

import twitter.beans.PasswordResetToken;
import twitter.beans.User;

/**
 * Created by Nikolay on 09.04.2017.
 */
public interface PasswordResetRepository {
  Long create(PasswordResetToken resetToken);

  PasswordResetToken read(Long id);

  void update(PasswordResetToken resetToken);

  PasswordResetToken findByToken(String token);

  User getUserByToken(String token);

}

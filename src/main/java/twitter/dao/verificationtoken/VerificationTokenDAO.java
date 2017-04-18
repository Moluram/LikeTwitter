package twitter.dao.verificationtoken;

import twitter.beans.User;
import twitter.beans.VerificationToken;

/**
 * Created by Nikolay on 08.04.2017.
 */
public interface VerificationTokenDAO {

  Long create(VerificationToken verificationToken);

  VerificationToken read(Long id);

  void update(VerificationToken verificationToken);

  VerificationToken findByTokenName(String token);

  User getUserByToken(String token);

}

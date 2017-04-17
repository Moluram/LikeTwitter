package twitter.dao.verificationtoken;

import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.dao.exception.DAOException;

/**
 * Created by Nikolay on 08.04.2017.
 */
public interface VerificationTokenDAO {

  Long create(VerificationToken verificationToken);

  VerificationToken read(Long id) throws DAOException;

  void update(VerificationToken verificationToken);

  VerificationToken findByTokenName(String token) throws DAOException;

  User getUserByToken(String token) throws DAOException;

}

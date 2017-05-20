package twitter.dao;

import twitter.entity.PasswordResetToken;
import twitter.entity.User;
import twitter.dao.exception.DAOException;

/**
 * Created by Nikolay on 09.04.2017.
 */
public interface IPasswordResetDAO {
  Long create(PasswordResetToken resetToken);

  PasswordResetToken read(Long id) throws DAOException;

  void update(PasswordResetToken resetToken);

  PasswordResetToken findByToken(String token) throws DAOException;

  User getUserByToken(String token) throws DAOException;

}

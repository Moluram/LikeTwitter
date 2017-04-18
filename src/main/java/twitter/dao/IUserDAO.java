package twitter.dao;

import twitter.beans.User;
import twitter.dao.exception.DAOException;

/**
 * Created by Nikolay on 14.04.2017.
 */
public interface IUserDAO extends IGenericDAO<User> {
  User findByUsername(String username) throws DAOException;

  User findByEmail(String email) throws DAOException;
}

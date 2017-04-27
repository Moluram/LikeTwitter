package twitter.dao;

import java.util.List;
import twitter.beans.Entity;
import twitter.dao.exception.DAOException;

/**
 * Created by Nikolay on 14.04.2017.
 */
public interface IGenericDAO<T extends Entity> {
  Long create(T instance);

  T read(Long id) throws DAOException;

  void update(T instance);

  void delete(Long id);

  List<T> getAll();
}

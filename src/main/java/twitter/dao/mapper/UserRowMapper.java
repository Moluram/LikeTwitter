package twitter.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import twitter.beans.Role;
import twitter.beans.User;
import twitter.beans.UserProfile;
import twitter.dao.IUserProfileDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.exception.DAOException;
import twitter.dao.IRoleDAO;

/**
 * Created by Nikolay on 15.04.2017.
 */
public class UserRowMapper extends EntityRowMapper<User> {

  private IRoleDAO roleDao;
  private IUserProfileDAO userProfileDAO;

  public UserRowMapper(IRoleDAO roleDao, IUserProfileDAO userProfileDAO) {
    this.userProfileDAO = userProfileDAO;
    this.roleDao = roleDao;
  }

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    Long id = resultSet.getLong(EntityColumn.COLUMN_ID);
    user.setId(id);
    user.setUsername(resultSet.getString(EntityColumn.COLUMN_USERNAME));
    user.setPassword(resultSet.getString(EntityColumn.COLUMN_PASSWORD));
    user.setEmail(resultSet.getString(EntityColumn.COLUMN_EMAIL));
    Boolean enabled = Boolean.valueOf(resultSet.getString(EntityColumn.COLUMN_ENABLED));
    user.setEnabled(enabled);
    Boolean tokenExpired = Boolean.valueOf(resultSet.getString(EntityColumn.COLUMN_TOKEN_EXPIRED));
    user.setTokenExpired(tokenExpired);
    Boolean isBaned = Boolean.valueOf(resultSet.getString(EntityColumn.COLUMN_IS_BANED));
    user.setBaned(isBaned);
    user.setRole(readRole(id));
    user.setUserProfile(readUserProfile(id));
    return user;
  }

  private Role readRole(Long id) {
    List<Long> roleId = readRelatedObjectsId(id, EntityType.TYPE_USER, EntityType.TYPE_ROLE);
    if (roleId.size() > 0) {
      return roleDao.read(roleId.get(0));
    }
    return null;
  }

  private UserProfile readUserProfile(Long id) {
    List<Long> userProfileId = readRelatedObjectsId(id, EntityType.TYPE_USER,
        EntityType.TYPE_USER_PROFILE);
    if (userProfileId.size() > 0) {
      try {
        return userProfileDAO.read(userProfileId.get(0));
      } catch (DAOException e) {
        return null;
      }
    }
    return null;
  }

}

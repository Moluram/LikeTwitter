package twitter.dao.impl;

import java.nio.file.attribute.UserPrincipal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import twitter.beans.Entity;
import twitter.beans.User;
import twitter.beans.UserProfile;
import twitter.dao.AbstractGenericDAOImpl;
import twitter.dao.UserDAO;
import twitter.dao.UserProfileDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.exception.DAOException;
import twitter.dao.mapper.UserRowMapper;
import twitter.dao.query.SqlQuery;
import twitter.dao.role.RoleDAO;

/**
 * Created by Nikolay on 14.04.2017.
 */
@Component
public class UserDAOImpl extends AbstractGenericDAOImpl<User> implements UserDAO {

  private final RoleDAO roleDAO;
  private final UserProfileDAO userProfileDAO;

  @Autowired
  public UserDAOImpl(DataSource dataSource, RoleDAO roleDAO,UserProfileDAO userProfileDAO) {
    super(dataSource);
    this.roleDAO = roleDAO;
    this.userProfileDAO=userProfileDAO;
  }

  @PostConstruct
  protected void initialize() {
    setObjectType(EntityType.TYPE_USER);
    setRelatedObjType(EntityType.TYPE_ROLE);
    setColumIdNames(new String[]{EntityColumn.COLUMN_ID});
    setRowMapper(new UserRowMapper(roleDAO,userProfileDAO));
    super.initialize();
  }

  @Override
  protected Collection<Long> getReferencesIds(User instance) {
    return Arrays.asList(instance.getRole().getId(),instance.getUserProfile().getId());
  }

  @Override
  protected Map<String, String> getAttrValueMap(User instance) {
    Map<String, String> attributeValueMap = new HashMap<>(5);
    attributeValueMap.put(EntityColumn.COLUMN_USERNAME, instance.getUsername());
    attributeValueMap.put(EntityColumn.COLUMN_PASSWORD, instance.getPassword());
    attributeValueMap.put(EntityColumn.COLUMN_EMAIL, instance.getEmail());
    attributeValueMap.put(EntityColumn.COLUMN_ENABLED, instance.isEnabled().toString());
    attributeValueMap.put(EntityColumn.COLUMN_TOKEN_EXPIRED, instance.isTokenExpired().toString());
    return attributeValueMap;
  }

  @Override
  protected String getReadQuery() {
    return SqlQuery.READ_ALL_USERS.getQuery();
  }

  @Override
  public User findByUsername(String username) throws DAOException {
    return readBy(EntityColumn.COLUMN_USERNAME, username);
  }

  @Override
  public User findByEmail(String email) throws DAOException {
    return readBy(EntityColumn.COLUMN_EMAIL, email);
  }
}

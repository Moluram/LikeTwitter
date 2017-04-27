package twitter.dao.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.beans.User;
import twitter.dao.IUserDAO;
import twitter.dao.IUserProfileDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.exception.DAOException;
import twitter.dao.mapper.UserRowMapper;
import twitter.dao.query.SqlQuery;
import twitter.dao.IRoleDAO;

/**
 * Created by Nikolay on 14.04.2017.
 */
@Component
public class UserDAOImpl extends AbstractGenericDAOImpl<User> implements IUserDAO {

  private final IRoleDAO roleDAO;
  private final IUserProfileDAO userProfileDAO;

  @Autowired
  public UserDAOImpl(DataSource dataSource, IRoleDAO roleDAO,IUserProfileDAO userProfileDAO) {
    super(dataSource);
    this.roleDAO = roleDAO;
    this.userProfileDAO=userProfileDAO;
  }

  @Override
  public User findByUsername(String username) throws DAOException {
    return readUnique(EntityColumn.COLUMN_USERNAME, username);
  }

  @Override
  public User findByEmail(String email) throws DAOException {
    return readUnique(EntityColumn.COLUMN_EMAIL, email);
  }

  @PostConstruct
  protected void initialize() {
    setObjectType(EntityType.TYPE_USER);
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

}

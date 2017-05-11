package twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.beans.PasswordResetToken;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.mapper.PasswordResetRowMapper;
import twitter.dao.mapper.VerificationTokenRowMapper;
import twitter.dao.query.SqlQuery;
import twitter.dao.utils.DateUtils;
import twitter.dao.IPasswordResetDAO;
import twitter.dao.IUserDAO;
import twitter.dao.exception.DAOException;

/**
 * Created by Nikolay on 09.04.2017.
 */
@Component()
public class PasswordResetDAOImpl extends AbstractGenericDAOImpl<PasswordResetToken> implements
    IPasswordResetDAO {

  private final IUserDAO userDAO;
  private final DateUtils dateUtils;

  @Autowired
  public PasswordResetDAOImpl(DataSource dataSource, IUserDAO userDAO, DateUtils dateUtils) {
    super(dataSource);
    this.userDAO = userDAO;
    this.dateUtils = dateUtils;
  }

  @PostConstruct
  protected void initialize() {
    setObjectType(EntityType.TYPE_PASSWORD_RESET_TOKEN);
    setColumIdNames(new String[]{EntityColumn.COLUMN_ID});
    setRowMapper(new PasswordResetRowMapper(userDAO, dateUtils));
    super.initialize();
  }

  @Override
  protected Collection<Long> getReferencesIds(PasswordResetToken instance) {
    return Arrays.asList(instance.getUser().getId());
  }

  @Override
  protected Map<String, String> getAttrValueMap(PasswordResetToken instance) {
    Map<String, String> attributeValueMap = new HashMap<>(2);
    attributeValueMap.put(EntityColumn.COLUMN_PASSWORD_RESET_TOKEN, instance.getToken());
    attributeValueMap
        .put(EntityColumn.COLUMN_EXPIRE_DATE, dateUtils.dateToStr(instance.getExpiryDate()));
    return attributeValueMap;
  }

  @Override
  protected String getReadQuery() {
    return SqlQuery.READ_ALL_PASSWORD_RESET_TOKENS.getQuery();
  }

  @Override
  public PasswordResetToken findByToken(String name) throws DAOException {
    return readUnique(EntityColumn.COLUMN_PASSWORD_RESET_TOKEN, name);
  }

  @Override
  public User getUserByToken(String token) throws DAOException {
    return findByToken(token).getUser();
  }
}

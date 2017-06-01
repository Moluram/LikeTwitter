package twitter.dao.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter.entity.User;
import twitter.entity.VerificationToken;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.mapper.VerificationTokenRowMapper;
import twitter.dao.query.SqlQuery;
import twitter.dao.utils.DateUtils;
import twitter.dao.IUserDAO;
import twitter.dao.IVerificationTokenDAO;
import twitter.dao.exception.DAOException;

/**
 * Created by Nikolay on 08.04.2017.
 */
@Repository
public class VerificationTokenDAOImpl extends AbstractGenericDAOImpl<VerificationToken> implements
    IVerificationTokenDAO {

  private final IUserDAO userDAO;
  private final DateUtils dateUtils;

  @Autowired
  public VerificationTokenDAOImpl(DataSource dataSource, IUserDAO userDAO, DateUtils dateUtils) {
    super(dataSource);
    this.userDAO = userDAO;
    this.dateUtils = dateUtils;
  }

  @PostConstruct
  protected void initialize() {
    setObjectType(EntityType.TYPE_VERIFICATION_TOKEN);
    setColumnIdNames(new String[]{EntityColumn.COLUMN_ID});
    setRowMapper(new VerificationTokenRowMapper(userDAO, dateUtils));
    super.initialize();
  }

  @Override
  protected Collection<Long> getReferencesIds(VerificationToken instance) {
    return Arrays.asList(instance.getUser().getId());
  }

  @Override
  protected Map<String, String> getAttrValueMap(VerificationToken instance) {
    Map<String, String> attributeValueMap = new HashMap<>(2);
    attributeValueMap.put(EntityColumn.COLUMN_VERIFICATION_TOKEN, instance.getToken());
    attributeValueMap
        .put(EntityColumn.COLUMN_EXPIRE_DATE, dateUtils.dateToStr(instance.getExpiryDate()));
    return attributeValueMap;
  }

  @Override
  protected String getReadQuery() {
    return SqlQuery.READ_ALL_VERIFICATION_TOKENS.getQuery();
  }

  @Override
  public VerificationToken findByTokenName(String name) throws DAOException {
    return readUnique(EntityColumn.COLUMN_VERIFICATION_TOKEN, name);
  }

  @Override
  public User getUserByToken(String token) throws DAOException {
    return findByTokenName(token).getUser();
  }

}

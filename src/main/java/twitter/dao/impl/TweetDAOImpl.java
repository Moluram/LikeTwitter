package twitter.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter.beans.Tweet;
import twitter.dao.ITweetDao;
import twitter.dao.IUserDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.exception.DAOException;
import twitter.dao.mapper.TweetRowMapper;
import twitter.dao.query.SqlQuery;
import twitter.dao.utils.DateUtils;

/**
 * Created by Nikolay on 23.04.2017.
 */
@Repository
public class TweetDAOImpl extends AbstractGenericDAOImpl<Tweet> implements ITweetDao {

  private final DateUtils dateUtils;

  @Autowired
  public TweetDAOImpl(DataSource dataSource, IUserDAO userDAO, DateUtils dateUtils) {
    super(dataSource);
    this.dateUtils = dateUtils;
  }

  @PostConstruct
  protected void initialize() {
    setObjectType(EntityType.TYPE_TWEET);
    setColumnIdNames(new String[]{EntityColumn.COLUMN_ID});
    setRowMapper(new TweetRowMapper(dateUtils));
    super.initialize();
  }

  @Override
  protected Map<String, String> getAttrValueMap(Tweet instance) {
    Map<String, String> attributeValueMap = new HashMap<>(2);
    attributeValueMap.put(EntityColumn.COLUMN_TEXT, instance.getText());
    attributeValueMap
        .put(EntityColumn.COLUMN_DATE, dateUtils.dateToStr(instance.getDate()));
    attributeValueMap.put(EntityColumn.COLUMN_OWNER_USERNAME,instance.getOwnerUsername());
    return attributeValueMap;
  }

  @Override
  protected String getReadQuery() {
    return SqlQuery.READ_ALL_TWEETS.getQuery();
  }

  @Override
  public List<Tweet> getUserTweets(String username) throws DAOException {
    return readBy(EntityColumn.COLUMN_OWNER_USERNAME,username);
  }
}

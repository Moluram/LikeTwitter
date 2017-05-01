package twitter.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.beans.Comment;
import twitter.dao.ICommentDAO;
import twitter.dao.IUserDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.mapper.CommentRowMapper;
import twitter.dao.query.SqlQuery;
import twitter.dao.utils.DateUtils;

/**
 * Created by Nikolay on 27.04.2017.
 */
@Component
public class CommentDAOImpl extends AbstractGenericDAOImpl<Comment> implements ICommentDAO {

  private IUserDAO userDAO;
  private DateUtils dateUtils;

  @Autowired
  public CommentDAOImpl(DataSource dataSource, IUserDAO userDAO, DateUtils dateUtils) {
    super(dataSource);
    this.userDAO = userDAO;
    this.dateUtils = dateUtils;
  }

  protected void initialize() {
    setObjectType(EntityType.TYPE_COMMENT);
    setColumIdNames(new String[]{EntityColumn.COLUMN_ID});
    setRowMapper(new CommentRowMapper(userDAO, dateUtils));
    super.initialize();
  }

  @Override
  protected Collection<Long> getReferencesIds(Comment instance) {
    Collection<Long> ids=new ArrayList<>();
    ids.add(instance.getUser().getId());
    if(instance.getParentCommentId()!=null){
      ids.add(instance.getParentCommentId());
    }
    return ids;
  }

  @Override
  protected Map<String, String> getAttrValueMap(Comment instance) {
    Map<String, String> attributeValueMap = new HashMap<>(3);
    attributeValueMap.put(EntityColumn.COLUMN_TEXT, instance.getText());
    attributeValueMap.put(EntityColumn.COLUMN_OWNER_TWEET_ID, instance.getTweetId().toString());
    attributeValueMap
        .put(EntityColumn.COLUMN_DATE, dateUtils.dateToStr(instance.getDate()));
    return attributeValueMap;
  }

  @Override
  protected String getReadQuery() {
    return SqlQuery.READ_ALL_COMMENTS.getQuery();
  }

  @Override
  public List<Comment> readByTweetId(Long tweetId) {
    return readBy(EntityColumn.COLUMN_OWNER_TWEET_ID,tweetId.toString());
  }
}

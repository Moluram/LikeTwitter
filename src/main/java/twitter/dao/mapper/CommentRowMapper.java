package twitter.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import twitter.beans.Comment;
import twitter.beans.Role;
import twitter.beans.User;
import twitter.beans.UserProfile;
import twitter.dao.ICommentDAO;
import twitter.dao.IUserDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.exception.DAOException;
import twitter.dao.utils.DateUtils;

/**
 * Created by Nikolay on 27.04.2017.
 */
public class CommentRowMapper extends EntityRowMapper<Comment> {

  private IUserDAO userDAO;
  private DateUtils dateUtils;

  public CommentRowMapper(IUserDAO userDAO, DateUtils dateUtils) {
    this.userDAO = userDAO;
    this.dateUtils = dateUtils;
  }

  @Override
  public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
    System.out.println("ROW MAPPER");
    Comment comment = new Comment();
    Long id = resultSet.getLong(EntityColumn.COLUMN_ID);
    comment.setId(id);
    comment.setText(resultSet.getString(EntityColumn.COLUMN_TEXT));
    Date date = null;
    try {
      date = dateUtils.strToDate(resultSet.getString(EntityColumn.COLUMN_DATE));
    } catch (ParseException e) {
      throw new SQLException("Can't parse comment date!");
    }
    comment.setDate(date);
    Long tweetId = Long.parseLong(resultSet.getString(EntityColumn.COLUMN_OWNER_TWEET_ID));
    comment.setTweetId(tweetId);
    comment.setParentCommentId(readParentCommentId(id));
    comment.setUser(readUser(id));
    return comment;
  }

  private Long readParentCommentId(Long id) throws SQLException {
    List<Long> commentId = readRelatedObjectsId(id, EntityType.TYPE_COMMENT,
        EntityType.TYPE_COMMENT);
    if (commentId.size() == 0) {
      return null;
    } else if (commentId.size() == 1) {
      return commentId.get(0);
    } else {
      throw new SQLException(
          "Incorrect result size of parent comment id: exepted 0 or 1, actual " + commentId.size());
    }
  }

  private User readUser(Long id) {
    List<Long> userId = readRelatedObjectsId(id, EntityType.TYPE_COMMENT,
        EntityType.TYPE_USER);
    if (userId.size() > 0) {
      return userDAO.read(userId.get(0));
    }
    return null;
  }
}

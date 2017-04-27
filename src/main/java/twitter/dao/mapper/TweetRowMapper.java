package twitter.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import twitter.beans.Tweet;
import twitter.dao.IUserDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.utils.DateUtils;

/**
 * Created by Nikolay on 23.04.2017.
 */
public class TweetRowMapper extends EntityRowMapper<Tweet> {

  private DateUtils dateUtils;

  public TweetRowMapper(DateUtils dateUtils) {
    this.dateUtils = dateUtils;
  }

  @Override
  public Tweet mapRow(ResultSet resultSet, int i) throws SQLException {
    Tweet tweet=new Tweet();
    tweet.setId(resultSet.getLong(EntityColumn.COLUMN_ID));
    tweet.setText(resultSet.getString(EntityColumn.COLUMN_TEXT));
    Date date=null;
    try {
      date = dateUtils.strToDate(resultSet.getString(EntityColumn.COLUMN_DATE));
    } catch (ParseException e) {
      throw new SQLException("Can't parse expire date!");
    }
    tweet.setDate(date);
    tweet.setOwnerUsername(resultSet.getString(EntityColumn.COLUMN_OWNER_USERNAME));
    return tweet;
  }
}

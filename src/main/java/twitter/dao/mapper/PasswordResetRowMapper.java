package twitter.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import twitter.beans.PasswordResetToken;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.dao.IUserDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.utils.DateUtils;

/**
 * Created by Nikolay on 17.04.2017.
 */
public class PasswordResetRowMapper extends EntityRowMapper<PasswordResetToken> {

  private IUserDAO userDAO;
  private DateUtils dateUtils;

  public PasswordResetRowMapper(IUserDAO userDAO, DateUtils dateUtils) {
    this.userDAO = userDAO;
    this.dateUtils = dateUtils;
  }

  @Override
  public PasswordResetToken mapRow(ResultSet resultSet, int i) throws SQLException {
    PasswordResetToken passwordResetToken = new PasswordResetToken();
    Long id = resultSet.getLong(EntityColumn.COLUMN_ID);
    passwordResetToken.setId(id);
    passwordResetToken.setToken(resultSet.getString(EntityColumn.COLUMN_PASSWORD_RESET_TOKEN));
    Date expireDate = null;
    try {
      expireDate = dateUtils.strToDate(resultSet.getString(EntityColumn.COLUMN_EXPIRE_DATE));
    } catch (ParseException e) {
      throw new SQLException("Can't parse expire date!");
    }
    passwordResetToken.setExpiryDate(expireDate);
    passwordResetToken.setUser(readUser(id));
    return passwordResetToken;
  }

  private User readUser(Long tokenId) {
    List<Long> userId = readRelatedObjectsId(tokenId, EntityType.TYPE_PASSWORD_RESET_TOKEN,
        EntityType.TYPE_USER);
    if (userId.size() > 0) {
      return userDAO.read(userId.get(0));
    }
    return null;
  }
}

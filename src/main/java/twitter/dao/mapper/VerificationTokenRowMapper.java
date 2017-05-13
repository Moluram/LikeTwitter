package twitter.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import twitter.beans.Role;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.dao.IUserDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.utils.DateUtils;

/**
 * Created by Nikolay on 17.04.2017.
 */
public class VerificationTokenRowMapper extends EntityRowMapper<VerificationToken> {

  private IUserDAO userDAO;
  private DateUtils dateUtils;

  public VerificationTokenRowMapper(IUserDAO userDAO, DateUtils dateUtils) {
    this.userDAO = userDAO;
    this.dateUtils = dateUtils;
  }

  @Override
  public VerificationToken mapRow(ResultSet resultSet, int i) throws SQLException {
    VerificationToken verificationToken = new VerificationToken();
    Long id = resultSet.getLong(EntityColumn.COLUMN_ID);
    verificationToken.setId(id);
    verificationToken.setToken(resultSet.getString(EntityColumn.COLUMN_VERIFICATION_TOKEN));
    Date expireDate = null;
    try {
      expireDate = dateUtils.strToDate(resultSet.getString(EntityColumn.COLUMN_EXPIRE_DATE));
    } catch (ParseException e) {
      throw new SQLException("Can't parse expire date!");
    }
    verificationToken.setExpiryDate(expireDate);
    verificationToken.setUser(readUser(id));
    return verificationToken;
  }

  private User readUser(Long tokenId) {
    List<Long> userId = readRelatedObjectsId(tokenId, EntityType.TYPE_VERIFICATION_TOKEN,
        EntityType.TYPE_USER);
    if (userId.size() > 0) {
      return userDAO.read(userId.get(0));
    }
    return null;
  }
}

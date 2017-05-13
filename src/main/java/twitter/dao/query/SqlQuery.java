package twitter.dao.query;

import java.util.ResourceBundle;

/**
 * Created by Nikolay on 16.04.2017.
 */
public enum SqlQuery {
  INSERT_ENTITY, INSERT_ATTRIBUTE, INSERT_REFERENCE, UPDATE_ATTRIBUTE, DELETE_ENTITY,
  READ_REFERENCES_ID, READ_ALL_USERS, READ_ALL_USER_PROFILES, READ_ALL_ROLES,
  READ_ALL_TWEETS, READ_ALL_VERIFICATION_TOKENS, READ_ALL_PASSWORD_RESET_TOKENS,
  READ_ALL_COMMENTS, READ_ALL_SUBSCRIBES;

  public String getQuery() {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlquery");
    return resourceBundle.getString("query." + this.name().toLowerCase());
  }
}

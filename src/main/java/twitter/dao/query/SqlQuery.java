package twitter.dao.query;

import java.util.ResourceBundle;

/**
 * Created by Nikolay on 16.04.2017.
 */
public enum SqlQuery {
  INSERT_ENTITY, INSERT_ATTRIBUTE, INSERT_REFERENCE, UPDATE_ATTRIBUTE, DELETE_ENTITY,
  READ_REFERENCIES_ID, READ_OBJECT_ID_BY_VALUE, READ_ALL_USERS, READ_ALL_USER_PROFILES, READ_ALL_ROLES;

  public String getQuery() {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlquery");
    return resourceBundle.getString("query." + this.name().toLowerCase());
  }
}

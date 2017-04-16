package twitter.dao.query;

import java.util.ResourceBundle;

/**
 * Created by Nikolay on 16.04.2017.
 */
public enum SqlQuery {
  INSERT_ENTITY, INSERT_ATTRIBUTE, INSERT_REFERENCE, UPDATE_ATTRIBUTE, DELETE_ENTITY,
  READ_REFERENCIES_ID, READ_OBJECT_ID_BY_VALUE;

  public String getQuery() {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlquery");
    return resourceBundle.getString("sqlquery." + this.name().toLowerCase());
  }
}

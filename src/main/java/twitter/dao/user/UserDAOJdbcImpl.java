package twitter.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.beans.User;

/**
 * Created by Nikolay on 03.04.2017.
 */
@Component
public class UserDAOJdbcImpl implements UserDAO {

  private static final String QUERY_INSERT_USER_ATTRIBUTES_VALUES =
      "INSERT INTO attribute_value(entity_id,attribute_id,value) VALUES"
          + "(?,(SELECT attribute_id FROM attribute WHERE name=?),?)";
  private static final String QUERY_UPDATE_USER_ATTRIBUTES_VALUES = "UPDATE attribute_value "
      + "SET value=? "
      + "WHERE entity_id=? AND attribute_id=?";
  private static final String QUERY_CREATE_USER_ENTITY = "INSERT INTO object(type_id) VALUES"
      + "((SELECT type_id FROM object_type WHERE type_name=?))";
  private static final String OBJECT_TYPE = "user";

  private final DataSource dataSource;

  @Autowired
  public UserDAOJdbcImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Integer create(User user) {
    Integer id = null;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement createEntitySt = connection.prepareStatement(QUERY_CREATE_USER_ENTITY,
            Statement.RETURN_GENERATED_KEYS);
        PreparedStatement addAttrValuesSt = connection
            .prepareStatement(QUERY_INSERT_USER_ATTRIBUTES_VALUES)
    ) {
      id = createObjectEntity(createEntitySt);
      user.setId(id);
      insertUserAttrValues(addAttrValuesSt, user);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }

  @Override
  public User read(Integer id) {
    return null;
  }

  @Override
  public void update(User user) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement addAttrValuesSt = connection
            .prepareStatement(QUERY_UPDATE_USER_ATTRIBUTES_VALUES)
    ) {
      updateUserAttrValues(addAttrValuesSt, user);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(User user) {

  }

  @Override
  public List<User> getAll() {
    return null;
  }

  private Integer createObjectEntity(PreparedStatement createEntitySt) throws SQLException {
    createEntitySt.setString(1, OBJECT_TYPE);
    int effectedRows = createEntitySt.executeUpdate();
    if (effectedRows == 0) {
      throw new SQLException("Creating user failed, no rows affected.");
    }
    Integer id;
    try (ResultSet genereredKeys = createEntitySt.getGeneratedKeys()) {
      if (genereredKeys.next()) {
        id = genereredKeys.getInt(1);
      } else {
        throw new SQLException("Creating user failed, no ID obtained.");
      }
    }
    return id;
  }

  private void insertUserAttrValues(PreparedStatement addAttrValuesSt, User user)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(user);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      addAttrValuesSt.setInt(1, user.getId());
      addAttrValuesSt.setString(2, entry.getKey());
      addAttrValuesSt.setString(3, entry.getValue());
      addAttrValuesSt.addBatch();
    }
    addAttrValuesSt.executeBatch();
  }

  private void updateUserAttrValues(PreparedStatement addAttrValuesSt, User user)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(user);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      addAttrValuesSt.setString(1, entry.getValue());
      addAttrValuesSt.setInt(2, user.getId());
      addAttrValuesSt.setString(3,entry.getKey() );
      addAttrValuesSt.addBatch();
    }
    addAttrValuesSt.executeBatch();
  }

  private Map<String, String> getAttrValueMap(User user) {
    Map<String, String> attributeValueMap = new HashMap<String,String>(5);
    attributeValueMap.put("username", user.getUsername());
    attributeValueMap.put("password", user.getPassword());
    attributeValueMap.put("email", user.getEmail());
    attributeValueMap.put("enabled", user.isEnabled().toString());
    attributeValueMap.put("token_expired", user.isTokenExpired().toString());
    return attributeValueMap;
  }
}

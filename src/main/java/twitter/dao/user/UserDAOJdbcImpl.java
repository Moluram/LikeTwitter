package twitter.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

  private static final  String QUERY_READ_ALL_USERS="SELECT "
        + "obj.entity_id id,"
        + "username.value username,"
        + "password.value password,"
        + "email.value email,"
        + "enabled.value enabled,"
        + "token_expired.value token_expired "
      + "FROM object obj "
        + "JOIN attribute_value username ON obj.entity_id = username.entity_id "
          + "AND username.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE name='username') "
        + "JOIN attribute_value password ON obj.entity_id = password.entity_id "
          + "AND password.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE name='password') "
        + "JOIN attribute_value email ON obj.entity_id = email.entity_id "
          + "AND email.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE name='email') "
        + "JOIN attribute_value enabled ON obj.entity_id = enabled.entity_id "
          + "AND enabled.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE name='enabled') "
        + "JOIN attribute_value token_expired ON obj.entity_id = token_expired.entity_id "
          + "AND token_expired.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE name='token_expired') ";

  private static final String USER_ID_CONSTREIN=" WHERE obj.entity_id=? LIMIT 1";

  private static final String QUERY_DELETE_USER_OBJECT="DELETE FROM object WHERE entity_id=?";

  private static final String QUERY_DELETE_USER_ATTRIBUTES="DELETE FROM attribute_value WHERE entity_id=?";

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
    User user=null;
    String query=QUERY_READ_ALL_USERS+USER_ID_CONSTREIN;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setInt(1,id);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        user=new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        Boolean enabled=Boolean.getBoolean(rs.getString("enabled"));
        user.setEnabled(enabled);
        Boolean tokenExpired=Boolean.getBoolean((rs.getString("token_expired")));
        user.setTokenExpired(tokenExpired);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
  }


  @Override
  public void update(User user) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement updAttrValuesSt = connection
            .prepareStatement(QUERY_UPDATE_USER_ATTRIBUTES_VALUES)
    ) {
      updateUserAttrValues(updAttrValuesSt, user);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Integer id) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement deleteObjectSt = connection
            .prepareStatement(QUERY_DELETE_USER_OBJECT);
        PreparedStatement deleteAttrsSt = connection
            .prepareStatement(QUERY_DELETE_USER_ATTRIBUTES)
    ) {
      deleteAttrsSt.setInt(1,id);
      int effectedRows=deleteAttrsSt.executeUpdate();
      if(effectedRows==0){
        throw new SQLException("Deleting user failed, no rows affected.");
      }
      deleteObjectSt.setInt(1,id);
      effectedRows=deleteObjectSt.executeUpdate();
      if(effectedRows==0){
        throw new SQLException("Deleting user failed, no rows affected.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<User> getAll() {
    List<User> userList=new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
        Statement getAllSt = connection.createStatement()) {
      ResultSet rs=getAllSt.executeQuery(QUERY_READ_ALL_USERS);
      while(rs.next()){
        User user=new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        Boolean enabled=Boolean.getBoolean(rs.getString("enabled"));
        user.setEnabled(enabled);
        Boolean tokenExpired=Boolean.getBoolean((rs.getString("token_expired")));
        user.setTokenExpired(tokenExpired);
        userList.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return userList;
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

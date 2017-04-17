package twitter.dao.verificationtoken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.dao.DateUtils;
import twitter.dao.UserDAO;
import twitter.dao.exception.DAOException;

/**
 * Created by Nikolay on 08.04.2017.
 */
@Component
public class VerificationTokenDAOJdbcImpl implements VerificationTokenDAO{

  private static final String QUERY_INSERT_TOKEN_ATTRIBUTES_VALUES =
      "INSERT INTO attribute_value(entity_id,attribute_id,value) VALUES"
          + "(?,(SELECT attribute_id FROM attribute WHERE attribute.name=?),?)";

  private static final String QUERY_INSERT_TOKEN_USER=
      "INSERT INTO reference(parent_id,child_id) VALUES(?,?)";

  private static final String QUERY_UPDATE_TOKEN_ATTRIBUTES_VALUES = "UPDATE attribute_value "
      + "SET value=? "
      + "WHERE entity_id=? AND attribute_id="
      + "(SELECT attribute_id FROM attribute WHERE attribute.name=?)";

  private static final String QUERY_CREATE_TOKEN_ENTITY = "INSERT INTO object(type_id) VALUES"
      + "((SELECT type_id FROM object_type WHERE type_name=?))";

  private static final  String QUERY_READ_ALL_TOKENS="SELECT "
      + "obj.entity_id id,"
      + "name.value v_token, "
      + "date.value expire_date "
      + "FROM object obj "
      + "JOIN attribute_value name ON obj.entity_id = name.entity_id "
      + "AND name.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE attribute.name='v_token') "
      + "JOIN attribute_value date ON obj.entity_id = date.entity_id "
      + "AND date.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE attribute.name='expire_date') ";;

  private static final String QUERY_GET_USER_TOKEN_ID="SELECT "
      + "reference.child_id user_id "
      + "FROM reference "
      + "INNER JOIN object parent_object ON parent_object.entity_id=reference.parent_id "
      + "INNER JOIN object child_object ON child_object.entity_id=reference.child_id "
      + "INNER JOIN object_type parent_type ON parent_object.type_id=parent_type.type_id "
      + "INNER JOIN object_type child_type ON child_object.type_id=child_type.type_id "
      + "WHERE reference.parent_id=? AND parent_type.type_name=? AND child_type.type_name=?";

  private static final String TOKEN_ID_CONSTREIN=" WHERE obj.entity_id=? LIMIT 1";

  private static final String TOKEN_CONSTREIN=" WHERE name.value=? LIMIT 1";

  private static final String QUERY_TOKEN_ID_BY_NAME="SELECT object.entity_id id FROM object "
      + "JOIN attribute_value ON object.entity_id=attribute_value.entity_id "
      + "WHERE attribute_id=(SELECT attribute_id FROM attribute WHERE name='v_token') "
      + "AND value=?";

  private static final String QUERY_DELETE_ROLE_OBJECT="DELETE FROM object WHERE entity_id=?";

  private static final String QUERY_DELETE_ROLE_ATTRIBUTES="DELETE FROM attribute_value WHERE entity_id=?";

  private static final String OBJECT_TYPE = "verification_token";

  private final DataSource dataSource;
  private final UserDAO userDAO;
  private  final DateUtils dateUtils;

  @Autowired
  public VerificationTokenDAOJdbcImpl(DataSource dataSource, UserDAO userDAO, DateUtils dateUtils) {
    this.dataSource = dataSource;
    this.userDAO=userDAO;
    this.dateUtils = dateUtils;
  }

  @Override
  public Long create(VerificationToken verificationToken) {
    Long id=null;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement createEntitySt = connection.prepareStatement(QUERY_CREATE_TOKEN_ENTITY,
            Statement.RETURN_GENERATED_KEYS);
        PreparedStatement addAttrValuesSt = connection
            .prepareStatement(QUERY_INSERT_TOKEN_ATTRIBUTES_VALUES);
        PreparedStatement addPrivilegesSt = connection
            .prepareStatement(QUERY_INSERT_TOKEN_USER);
    ) {
      id = createObjectEntity(createEntitySt);
      verificationToken.setId(id);
      insertTokenAttrValues(addAttrValuesSt, verificationToken);
      insertTokenUser(addPrivilegesSt,verificationToken);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }

  @Override
  public VerificationToken read(Long id) throws DAOException {
    VerificationToken verificationToken=null;
    String query=QUERY_READ_ALL_TOKENS+TOKEN_ID_CONSTREIN;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setLong(1,id);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        verificationToken=new VerificationToken();
        verificationToken.setId(rs.getLong("id"));
        verificationToken.setToken(rs.getString("v_token"));
        Date date = dateUtils.strToDate(rs.getString("expire_date"));
        verificationToken.setExpiryDate(date);
      }
      User user=getUserByToken(verificationToken.getToken());
      verificationToken.setUser(user);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return verificationToken;
  }

  @Override
  public void update(VerificationToken verificationToken) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement updAttrValuesSt = connection
            .prepareStatement(QUERY_UPDATE_TOKEN_ATTRIBUTES_VALUES)
    ) {
      updateTokenAttrValues(updAttrValuesSt, verificationToken);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public VerificationToken findByTokenName(String token) throws DAOException {
    VerificationToken verificationToken=null;
    String query=QUERY_READ_ALL_TOKENS+TOKEN_CONSTREIN;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setString(1,token);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        verificationToken=new VerificationToken();
        verificationToken.setId(rs.getLong("id"));
        verificationToken.setToken(rs.getString("v_token"));
        User user=getUserByToken(token);
        verificationToken.setUser(user);
        Date date = dateUtils.strToDate(rs.getString("expire_date"));
        verificationToken.setExpiryDate(date);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return verificationToken;
  }

  @Override
  public User getUserByToken(String token) throws DAOException {
    User user=null;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(QUERY_GET_USER_TOKEN_ID)) {
      Long token_id=getTokenIdByName(token);
      st.setLong(1,token_id);
      st.setString(2,OBJECT_TYPE);
      st.setString(3,"user");
      ResultSet resultSet=st.executeQuery();
      while(resultSet.next()){
        Long id=resultSet.getLong("user_id");
        user=userDAO.read(id);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
  }

  private Long getTokenIdByName(String name){
    Long id=null;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement getIdSt = connection.prepareStatement(QUERY_TOKEN_ID_BY_NAME);
    ) {
      getIdSt.setString(1,name);
      ResultSet rs=getIdSt.executeQuery();
      while(rs.next()){
        id=rs.getLong("id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }

  private Long createObjectEntity(PreparedStatement createEntitySt) throws SQLException {
    createEntitySt.setString(1, OBJECT_TYPE);
    int effectedRows = createEntitySt.executeUpdate();
    if (effectedRows == 0) {
      throw new SQLException("Creating token failed, no rows affected.");
    }
    Long id;
    try (ResultSet genereredKeys = createEntitySt.getGeneratedKeys()) {
      if (genereredKeys.next()) {
        id = genereredKeys.getLong(1);
      } else {
        throw new SQLException("Creating token failed, no ID obtained.");
      }
    }
    return id;
  }

  private void insertTokenAttrValues(PreparedStatement addAttrValuesSt, VerificationToken verificationToken)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(verificationToken);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      addAttrValuesSt.setLong(1, verificationToken.getId());
      addAttrValuesSt.setString(2, entry.getKey());
      addAttrValuesSt.setString(3, entry.getValue());
      addAttrValuesSt.addBatch();
    }
    addAttrValuesSt.executeBatch();
  }

  private void insertTokenUser(PreparedStatement st,VerificationToken verificationToken)throws SQLException{
      st.setLong(1,verificationToken.getId());
      st.setLong(2,verificationToken.getUser().getId());
      st.executeUpdate();
  }

  private void updateTokenAttrValues(PreparedStatement updateAttrValuesSt, VerificationToken verificationToken)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(verificationToken);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      updateAttrValuesSt.setString(1, entry.getValue());
      updateAttrValuesSt.setLong(2, verificationToken.getId());
      updateAttrValuesSt.setString(3,entry.getKey() );
      updateAttrValuesSt.addBatch();
    }
    updateAttrValuesSt.executeBatch();
  }

  private Map<String, String> getAttrValueMap(VerificationToken verificationToken) {
    Map<String, String> attributeValueMap = new HashMap<String,String>(1);
    attributeValueMap.put("v_token", verificationToken.getToken());
    attributeValueMap.put("expire_date",dateUtils.dateToStr(verificationToken.getExpiryDate()));
    return attributeValueMap;
  }
}

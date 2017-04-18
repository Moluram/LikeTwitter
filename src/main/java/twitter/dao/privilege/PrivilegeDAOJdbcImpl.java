package twitter.dao.privilege;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.beans.Privilege;

/**
 * Created by Nikolay on 06.04.2017.
 */
@Component
public class PrivilegeDAOJdbcImpl implements PrivilegeDAO {

  private static final String QUERY_INSERT_PRIVILEGE_ATTRIBUTES_VALUES =
      "INSERT INTO attribute_value(entity_id,attribute_id,value) VALUES"
          + "(?,(SELECT attribute_id FROM attribute WHERE attribute.name=?),?)";

  private static final String QUERY_UPDATE_PRIVILEGE_ATTRIBUTES_VALUES = "UPDATE attribute_value "
      + "SET value=? "
      + "WHERE entity_id=? AND attribute_id="
      + "(SELECT attribute_id FROM attribute WHERE attribute.name=?)";

  private static final String QUERY_CREATE_PRIVILEGE_ENTITY = "INSERT INTO object(type_id) VALUES"
      + "((SELECT type_id FROM object_type WHERE type_name=?))";

  private static final  String QUERY_READ_ALL_PRIVILEGES="SELECT "
      + "obj.entity_id id,"
      + "name.value name "
      + "FROM object obj "
      + "JOIN attribute_value name ON obj.entity_id = name.entity_id "
      + "AND name.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE attribute.name='name') ";

  private static final String BY_PRIVILEGE_ID_CONSTRAIN =" WHERE obj.entity_id=? LIMIT 1";

  private static final String BY_NAME_CONSTRAIN =" WHERE name.value=? LIMIT 1";

  private static final String BY_ROLE_ID_CONSTRAIN=" JOIN reference on obj.entity_id=reference.child_id "
      + "WHERE reference.parent_id=?";

  private static final String QUERY_DELETE_PRIVILEGE_OBJECT="DELETE FROM object WHERE entity_id=?";

  private static final String QUERY_DELETE_PRIVILEGE_ATTRIBUTES="DELETE FROM attribute_value WHERE entity_id=?";

  private static final String OBJECT_TYPE = "privilege";

  private final DataSource dataSource;

  @Autowired
  public PrivilegeDAOJdbcImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Long create(Privilege privilege) {
    Long id = null;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement createEntitySt = connection.prepareStatement(QUERY_CREATE_PRIVILEGE_ENTITY,
            Statement.RETURN_GENERATED_KEYS);
        PreparedStatement addAttrValuesSt = connection
            .prepareStatement(QUERY_INSERT_PRIVILEGE_ATTRIBUTES_VALUES)
    ) {
      id = createObjectEntity(createEntitySt);
      privilege.setId(id);
      insertPrivilegeAttrValues(addAttrValuesSt, privilege);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }

  @Override
  public Privilege read(Long id) {
    Privilege privilege=null;
    String query=QUERY_READ_ALL_PRIVILEGES+ BY_PRIVILEGE_ID_CONSTRAIN;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setLong(1,id);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        privilege=new Privilege();
        privilege.setId(rs.getLong("id"));
        privilege.setName(rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return privilege;
  }


  @Override
  public void update(Privilege privilege) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement updAttrValuesSt = connection
            .prepareStatement(QUERY_UPDATE_PRIVILEGE_ATTRIBUTES_VALUES)
    ) {
      updatePrivilegeAttrValues(updAttrValuesSt, privilege);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Long id) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement deleteObjectSt = connection
            .prepareStatement(QUERY_DELETE_PRIVILEGE_OBJECT);
        PreparedStatement deleteAttrsSt = connection
            .prepareStatement(QUERY_DELETE_PRIVILEGE_ATTRIBUTES)
    ) {
      deleteAttrsSt.setLong(1,id);
      int effectedRows=deleteAttrsSt.executeUpdate();
      if(effectedRows==0){
        throw new SQLException("Deleting privilege failed, no rows affected.");
      }
      deleteObjectSt.setLong(1,id);
      effectedRows=deleteObjectSt.executeUpdate();
      if(effectedRows==0){
        throw new SQLException("Deleting privilege failed, no rows affected.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Privilege> getAll() {
    List<Privilege> privilegeList=new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
        Statement getAllSt = connection.createStatement()) {
      ResultSet rs=getAllSt.executeQuery(QUERY_READ_ALL_PRIVILEGES);
      while(rs.next()){
        Privilege privilege=new Privilege();
        privilege.setId(rs.getLong("id"));
        privilege.setName(rs.getString("name"));
        privilegeList.add(privilege);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return privilegeList;
  }

  @Override
  public Privilege findByName(String name) {
    Privilege privilege=null;
    String query=QUERY_READ_ALL_PRIVILEGES+ BY_NAME_CONSTRAIN;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setString(1,name);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        privilege=new Privilege();
        privilege.setId(rs.getLong("id"));
        privilege.setName(rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return privilege;
  }

  public Collection<Privilege> getPrivilegesByRoleId(Long roleId){
    Collection<Privilege> privileges=new ArrayList<>();
    String query=QUERY_READ_ALL_PRIVILEGES+BY_ROLE_ID_CONSTRAIN;
    try(Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setLong(1,roleId);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        Privilege privilege=new Privilege();
        privilege.setId(rs.getLong("id"));
        privilege.setName(rs.getString("name"));
        privileges.add(privilege);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return privileges;
  }

  private Long createObjectEntity(PreparedStatement createEntitySt) throws SQLException {
    createEntitySt.setString(1, OBJECT_TYPE);
    int effectedRows = createEntitySt.executeUpdate();
    if (effectedRows == 0) {
      throw new SQLException("Creating privilege failed, no rows affected.");
    }
    Long id;
    try (ResultSet genereredKeys = createEntitySt.getGeneratedKeys()) {
      if (genereredKeys.next()) {
        id = genereredKeys.getLong(1);
      } else {
        throw new SQLException("Creating role failed, no ID obtained.");
      }
    }
    return id;
  }

  private void insertPrivilegeAttrValues(PreparedStatement addAttrValuesSt, Privilege privilege)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(privilege);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      addAttrValuesSt.setLong(1, privilege.getId());
      addAttrValuesSt.setString(2, entry.getKey());
      addAttrValuesSt.setString(3, entry.getValue());
      addAttrValuesSt.addBatch();
    }
    addAttrValuesSt.executeBatch();
  }

  private void updatePrivilegeAttrValues(PreparedStatement updateAttrValuesSt, Privilege privilege)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(privilege);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      updateAttrValuesSt.setString(1, entry.getValue());
      updateAttrValuesSt.setLong(2, privilege.getId());
      updateAttrValuesSt.setString(3,entry.getKey() );
      updateAttrValuesSt.addBatch();
    }
    updateAttrValuesSt.executeBatch();
  }

  private Map<String, String> getAttrValueMap(Privilege privilege) {
    Map<String, String> attributeValueMap = new HashMap<>(1);
    attributeValueMap.put("name", privilege.getName());
    return attributeValueMap;
  }
}

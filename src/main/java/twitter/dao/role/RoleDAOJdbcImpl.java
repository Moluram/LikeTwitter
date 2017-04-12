package twitter.dao.role;

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
import twitter.beans.Role;
import twitter.dao.privilege.PrivilegeDAO;

/**
 * Created by Nikolay on 06.04.2017.
 */
@Component
public class RoleDAOJdbcImpl implements RoleDAO {

  private static final String QUERY_INSERT_ROLE_ATTRIBUTES_VALUES =
      "INSERT INTO attribute_value(entity_id,attribute_id,value) VALUES"
          + "(?,(SELECT attribute_id FROM attribute WHERE attribute.name=?),?)";

  private static final String QUERY_INSERT_ROLE_PRIVILEGES=
      "INSERT INTO reference(parent_id,child_id) VALUES(?,?)";

  private static final String QUERY_UPDATE_ROLE_ATTRIBUTES_VALUES = "UPDATE attribute_value "
      + "SET value=? "
      + "WHERE entity_id=? AND attribute_id="
      + "(SELECT attribute_id FROM attribute WHERE attribute.name=?)";

  private static final String QUERY_CREATE_ROLE_ENTITY = "INSERT INTO object(type_id) VALUES"
      + "((SELECT type_id FROM object_type WHERE type_name=?))";

  private static final  String QUERY_READ_ALL_ROLES="SELECT "
        + "obj.entity_id id,"
        + "name.value name "
      + "FROM object obj "
        + "JOIN attribute_value name ON obj.entity_id = name.entity_id "
          + "AND name.attribute_id=(SELECT attribute.attribute_id FROM attribute WHERE attribute.name='name') ";

  private static final String USER_ID_CONSTREIN=" WHERE obj.entity_id=? LIMIT 1";

  private static final String NAME_CONSTREIN=" WHERE name.value=? LIMIT 1";

  private static final String QUERY_DELETE_ROLE_OBJECT="DELETE FROM object WHERE entity_id=?";

  private static final String QUERY_DELETE_ROLE_ATTRIBUTES="DELETE FROM attribute_value WHERE entity_id=?";

  private static final String OBJECT_TYPE = "role";

  private final DataSource dataSource;
  private final PrivilegeDAO privilegeDAO;

  @Autowired
  public RoleDAOJdbcImpl(DataSource dataSource,PrivilegeDAO privilegeDAO) {
    this.dataSource = dataSource;
    this.privilegeDAO=privilegeDAO;
  }

  @Override
  public Long create(Role role) {
    Long id = null;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement createEntitySt = connection.prepareStatement(QUERY_CREATE_ROLE_ENTITY,
            Statement.RETURN_GENERATED_KEYS);
        PreparedStatement addAttrValuesSt = connection
            .prepareStatement(QUERY_INSERT_ROLE_ATTRIBUTES_VALUES);
        PreparedStatement addPrivilegesSt = connection
            .prepareStatement(QUERY_INSERT_ROLE_PRIVILEGES);
    ) {
      id = createObjectEntity(createEntitySt);
      role.setId(id);
      insertRoleAttrValues(addAttrValuesSt, role);
      insertPrivileges(addPrivilegesSt,role);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }

  @Override
  public Role read(Long id) {
    Role role=null;
    String query=QUERY_READ_ALL_ROLES+USER_ID_CONSTREIN;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setLong(1,id);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        role=new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("name"));
      }
      Collection<Privilege> privileges=privilegeDAO.getPrivilegesByRoleId(role.getId());
      role.setPrivileges(privileges);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return role;
  }

  //TODO: update privileges ??
  @Override
  public void update(Role role) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement updAttrValuesSt = connection
            .prepareStatement(QUERY_UPDATE_ROLE_ATTRIBUTES_VALUES)
    ) {
      updateRoleAttrValues(updAttrValuesSt, role);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Long id) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement deleteObjectSt = connection
            .prepareStatement(QUERY_DELETE_ROLE_OBJECT);
        PreparedStatement deleteAttrsSt = connection
            .prepareStatement(QUERY_DELETE_ROLE_ATTRIBUTES)
    ) {
      deleteAttrsSt.setLong(1,id);
      int effectedRows=deleteAttrsSt.executeUpdate();
      if(effectedRows==0){
        throw new SQLException("Deleting role failed, no rows affected.");
      }
      deleteObjectSt.setLong(1,id);
      effectedRows=deleteObjectSt.executeUpdate();
      if(effectedRows==0){
        throw new SQLException("Deleting role failed, no rows affected.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Role> getAll() {
    List<Role> roleList=new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
        Statement getAllSt = connection.createStatement()) {
      ResultSet rs=getAllSt.executeQuery(QUERY_READ_ALL_ROLES);
      while(rs.next()){
        Role role=new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("name"));
        Collection<Privilege> privileges=privilegeDAO.getPrivilegesByRoleId(role.getId());
        role.setPrivileges(privileges);
        roleList.add(role);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return roleList;
  }

  @Override
  public Role findByName(String name) {
    Role role=null;
    String query=QUERY_READ_ALL_ROLES+NAME_CONSTREIN;
    try (Connection connection = dataSource.getConnection();
        PreparedStatement st = connection.prepareStatement(query)) {
      st.setString(1,name);
      ResultSet rs=st.executeQuery();
      while(rs.next()){
        role=new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("name"));
        Collection<Privilege> privileges=privilegeDAO.getPrivilegesByRoleId(role.getId());
        role.setPrivileges(privileges);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return role;
  }

  private Long createObjectEntity(PreparedStatement createEntitySt) throws SQLException {
    createEntitySt.setString(1, OBJECT_TYPE);
    int effectedRows = createEntitySt.executeUpdate();
    if (effectedRows == 0) {
      throw new SQLException("Creating role failed, no rows affected.");
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

  private void insertRoleAttrValues(PreparedStatement addAttrValuesSt, Role role)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(role);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      addAttrValuesSt.setLong(1, role.getId());
      addAttrValuesSt.setString(2, entry.getKey());
      addAttrValuesSt.setString(3, entry.getValue());
      addAttrValuesSt.addBatch();
    }
    addAttrValuesSt.executeBatch();
  }

  private void insertPrivileges(PreparedStatement st,Role role)throws SQLException{
    Collection<Privilege> privileges=role.getPrivileges();
    for(Privilege pr: privileges){
      st.setLong(1,role.getId());
      st.setLong(2,pr.getId());
      st.addBatch();
    }
    st.executeBatch();
  }

  private void updateRoleAttrValues(PreparedStatement updateAttrValuesSt, Role role)
      throws SQLException {
    Map<String, String> attributeValueMap = getAttrValueMap(role);
    for (Map.Entry<String, String> entry : attributeValueMap.entrySet()) {
      updateAttrValuesSt.setString(1, entry.getValue());
      updateAttrValuesSt.setLong(2, role.getId());
      updateAttrValuesSt.setString(3,entry.getKey() );
      updateAttrValuesSt.addBatch();
    }
    updateAttrValuesSt.executeBatch();
  }

  private Map<String, String> getAttrValueMap(Role role) {
    Map<String, String> attributeValueMap = new HashMap<String,String>(1);
    attributeValueMap.put("name", role.getName());
    return attributeValueMap;
  }
}

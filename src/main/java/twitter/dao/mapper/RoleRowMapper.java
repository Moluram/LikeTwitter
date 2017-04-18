package twitter.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import twitter.beans.Privilege;
import twitter.beans.Role;
import twitter.dao.IPrivilegeDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;

/**
 * Created by Nikolay on 17.04.2017.
 */
public class RoleRowMapper extends EntityRowMapper<Role> {

  private final IPrivilegeDAO privilegeDAO;

  @Autowired
  public RoleRowMapper(IPrivilegeDAO privilegeDAO) {
    this.privilegeDAO = privilegeDAO;
  }

  @Override
  public Role mapRow(ResultSet resultSet, int i) throws SQLException {
    Role role = new Role();
    Long roleId = resultSet.getLong(EntityColumn.COLUMN_ID);
    role.setId(roleId);
    role.setName(resultSet.getString(EntityColumn.COLUMN_NAME));
    role.setPrivileges(readPrivileges(roleId));
    return role;
  }

  //TODO: replase loop
  private List<Privilege> readPrivileges(Long roleId) {
    List<Long> privilegesId = readRelatedObjectsId(roleId, EntityType.TYPE_ROLE,
        EntityType.TYPE_PRIVILEGE);
    if (privilegesId.size() == 0) {
      return null;
    }
    List<Privilege> privileges = new ArrayList<>();
    for (Long id : privilegesId) {
      privileges.add(privilegeDAO.read(id));
    }
    return privileges;
  }
}

package twitter.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.beans.Entity;
import twitter.beans.Role;
import twitter.dao.IPrivilegeDAO;
import twitter.dao.IRoleDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.exception.DAOException;
import twitter.dao.mapper.RoleRowMapper;
import twitter.dao.query.SqlQuery;

/**
 * Created by Nikolay on 06.04.2017.
 */
@Component
public class RoleDAOImpl extends AbstractGenericDAOImpl<Role> implements IRoleDAO {

  private final IPrivilegeDAO privilegeDAO;

  @Autowired
  public RoleDAOImpl(DataSource dataSource, IPrivilegeDAO privilegeDAO) {
    super(dataSource);
    this.privilegeDAO = privilegeDAO;
  }

  @PostConstruct
  protected void initialize() {
    setObjectType(EntityType.TYPE_ROLE);
    setColumIdNames(new String[]{EntityColumn.COLUMN_ID});
    setRowMapper(new RoleRowMapper(privilegeDAO));
    super.initialize();
  }

  @Override
  protected Collection<Long> getReferencesIds(Role instance) {
    return instance.getPrivileges().stream().mapToLong(Entity::getId)
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }

  @Override
  protected Map<String, String> getAttrValueMap(Role instance) {
    Map<String, String> attributeValueMap = new HashMap<>(1);
    attributeValueMap.put(EntityColumn.COLUMN_NAME, instance.getName());
    return attributeValueMap;
  }

  @Override
  protected String getReadQuery() {
    return SqlQuery.READ_ALL_ROLES.getQuery();
  }

  @Override
  public Role findByName(String name) throws DAOException {
    return readUnique(EntityColumn.COLUMN_NAME, name);
  }
}

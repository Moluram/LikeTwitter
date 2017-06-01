package twitter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter.dao.IPrivilegeDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.mapper.PrivilegeRowMapper;
import twitter.dao.query.SqlQuery;
import twitter.entity.Privilege;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikolay on 06.04.2017.
 */
@Repository
public class PrivilegeDAOImpl extends AbstractGenericDAOImpl<Privilege> implements IPrivilegeDAO {

    @Autowired
    public PrivilegeDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @PostConstruct
    protected void initialize() {
        setObjectType(EntityType.TYPE_PRIVILEGE);
        setColumnIdNames(new String[]{EntityColumn.COLUMN_ID});
        setRowMapper(new PrivilegeRowMapper());
        super.initialize();
    }

    @Override
    protected String getReadQuery() {
        return SqlQuery.READ_ALL_PRIVILEGES.getQuery();
    }

    protected Map<String, String> getAttrValueMap(Privilege privilege) {
        Map<String, String> attributeValueMap = new HashMap<>(1);
        attributeValueMap.put(EntityColumn.COLUMN_NAME, privilege.getName());
        return attributeValueMap;
    }

    @Override
    public Privilege findByName(String name) {
        return readUnique(EntityColumn.COLUMN_NAME, name);
    }

}

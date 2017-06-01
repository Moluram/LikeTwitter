package twitter.dao.mapper;

import twitter.dao.constant.EntityColumn;
import twitter.entity.Privilege;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nborsuk on 01.06.2017.
 */
public class PrivilegeRowMapper extends EntityRowMapper<Privilege> {
    @Override
    public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
        Privilege privilege = new Privilege();
        privilege.setId(resultSet.getLong(EntityColumn.COLUMN_ID));
        privilege.setName(resultSet.getString(EntityColumn.COLUMN_NAME));
        return privilege;
    }
}

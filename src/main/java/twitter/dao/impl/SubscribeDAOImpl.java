package twitter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter.beans.Subscribe;
import twitter.dao.ISubscribeDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.mapper.SubscribeRowMapper;
import twitter.dao.query.SqlQuery;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nborsuk on 11.05.2017.
 */
@Repository
public class SubscribeDAOImpl extends AbstractGenericDAOImpl<Subscribe> implements ISubscribeDAO {

    @Autowired
    public SubscribeDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @PostConstruct
    protected void initialize() {
        setObjectType(EntityType.TYPE_SUBSCRIBE);
        setColumnIdNames(new String[]{EntityColumn.COLUMN_ID});
        setRowMapper(new SubscribeRowMapper());
        super.initialize();
    }

    @Override
    public Subscribe readByOwnerUsername(String ownerUsername) {
        return readUnique(EntityColumn.COLUMN_OWNER_USERNAME,ownerUsername);
    }

    @Override
    protected Map<String, String> getAttrValueMap(Subscribe instance) {
        Map<String, String> attributeValueMap = new HashMap<>(2);
        attributeValueMap.put(EntityColumn.COLUMN_OWNER_USERNAME, instance.getOwnerUsername());
        attributeValueMap.put(EntityColumn.COLUMN_SUBSCRIBES, instance.getSubscribes().toString());
        return attributeValueMap;
    }

    @Override
    protected String getReadQuery() {
        return SqlQuery.READ_ALL_SUBSCRIBES.getQuery();
    }
}

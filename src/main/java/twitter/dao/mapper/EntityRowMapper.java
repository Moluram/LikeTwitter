package twitter.dao.mapper;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import twitter.beans.Entity;
import twitter.dao.constant.EntityType;
import twitter.dao.query.SqlQuery;

/**
 * Created by Nikolay on 15.04.2017.
 */
public abstract class EntityRowMapper<T> implements RowMapper<T> {

  private JdbcTemplate jdbcTemplate;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  protected List<Long> readRelatedObjectsId(Long objId,String relObjType) {
    List<Long> ids = jdbcTemplate
        .queryForList(SqlQuery.READ_REFERENCIES_ID.getQuery(), Long.class, objId,
            EntityType.TYPE_USER,
            relObjType);
    return ids;
  }
}

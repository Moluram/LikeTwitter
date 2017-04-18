package twitter.dao.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import twitter.beans.UserProfile;
import twitter.dao.IUserProfileDAO;
import twitter.dao.constant.EntityColumn;
import twitter.dao.constant.EntityType;
import twitter.dao.mapper.UserProfileRowMapper;
import twitter.dao.query.SqlQuery;

/**
 * Created by Nikolay on 16.04.2017.
 */
@Component
public class UserProfileDAOImpl extends AbstractGenericDAOImpl<UserProfile> implements
    IUserProfileDAO {

  public UserProfileDAOImpl(DataSource dataSource) {
    super(dataSource);
  }

  @PostConstruct
  protected void initialize() {
    setObjectType(EntityType.TYPE_USER_PROFILE);
    setColumIdNames(new String[]{EntityColumn.COLUMN_ID});
    setRowMapper(new UserProfileRowMapper());
    super.initialize();
  }

  @Override
  protected Map<String, String> getAttrValueMap(UserProfile instance) {
    Map<String, String> attributeValueMap = new HashMap<>(5);
    attributeValueMap.put(EntityColumn.COLUMN_FIRST_NAME, instance.getFirstName());
    attributeValueMap.put(EntityColumn.COLUMN_LAST_NAME, instance.getLastName());
    attributeValueMap.put(EntityColumn.COLUMN_PHOTO_URL, instance.getPhotoUrl());
    String links="";
    if(instance.getLinks()!=null){
      links=instance.getLinks().toString();
    }
    attributeValueMap.put(EntityColumn.COLUMN_LINKS, links);
    attributeValueMap.put(EntityColumn.COLUMN_STATUS, instance.getStatus());
    return attributeValueMap;
  }

  @Override
  protected String getReadQuery() {
    return SqlQuery.READ_ALL_USER_PROFILES.getQuery();
  }
}

package twitter.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import twitter.beans.UserProfile;
import twitter.dao.constant.EntityColumn;

/**
 * Created by Nikolay on 16.04.2017.
 */
public class UserProfileRowMapper extends EntityRowMapper<UserProfile> {

  @Override
  public UserProfile mapRow(ResultSet resultSet, int i) throws SQLException {
    UserProfile userProfile=new UserProfile();
    userProfile.setId(resultSet.getLong(EntityColumn.COLUMN_ID));
    userProfile.setFirstName((resultSet.getString(EntityColumn.COLUMN_FIRST_NAME)));
    userProfile.setLastName((resultSet.getString(EntityColumn.COLUMN_LAST_NAME)));
    userProfile.setPhotoUrl(resultSet.getString(EntityColumn.COLUMN_PHOTO_URL));
    userProfile.setMiniPhoto(resultSet.getString(EntityColumn.COLUMN_MINI_PHOTO));
    String lisks=resultSet.getString(EntityColumn.COLUMN_LINKS);
    userProfile.setLinks(strToLinksList(lisks));
    userProfile.setStatus(resultSet.getString(EntityColumn.COLUMN_STATUS));
    return userProfile;
  }

  private List<String> strToLinksList(String links){
    String[] array = links
            .replace("[", "")
            .replace("]", "")
            .split(",");
    List<String> list=Arrays
            .stream(array)
            .filter(s -> !s.isEmpty())
            .map(String::trim)
            .collect(Collectors.toList());
    return list;
  }
}

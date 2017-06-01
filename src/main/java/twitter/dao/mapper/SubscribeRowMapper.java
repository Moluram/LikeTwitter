package twitter.dao.mapper;

import twitter.entity.Subscribe;
import twitter.dao.constant.EntityColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nborsuk on 11.05.2017.
 */
public class SubscribeRowMapper extends EntityRowMapper<Subscribe> {

    @Override
    public Subscribe mapRow(ResultSet resultSet, int i) throws SQLException {
        Subscribe subscribe = new Subscribe();
        subscribe.setId(resultSet.getLong(EntityColumn.COLUMN_ID));
        subscribe.setOwnerUsername(resultSet.getString(EntityColumn.COLUMN_OWNER_USERNAME));
        String subscribes = resultSet.getString(EntityColumn.COLUMN_SUBSCRIBES);
        subscribe.setSubscribes(strToSubscribeList(subscribes));
        return subscribe;
    }

    private List<String> strToSubscribeList(String subscribes) {
        String[] array = subscribes
                .replace("[", "")
                .replace("]", "")
                .split(",");
        List<String> list = Arrays
                .stream(array)
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
        return list;
    }
}

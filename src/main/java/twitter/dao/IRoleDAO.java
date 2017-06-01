package twitter.dao;

import java.util.List;

import twitter.entity.Role;

/**
 * Created by Nikolay on 05.04.2017.
 */
public interface IRoleDAO extends IGenericDAO<Role> {

    Role findByName(String name);

}

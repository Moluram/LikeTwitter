package twitter.dao;


import twitter.entity.Privilege;

/**
 * Created by Nikolay on 06.04.2017.
 */
public interface IPrivilegeDAO extends IGenericDAO<Privilege> {

    Privilege findByName(String name);

}

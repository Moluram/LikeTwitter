package twitter.dao;

import twitter.entity.Subscribe;

/**
 * Created by nborsuk on 11.05.2017.
 */
public interface ISubscribeDAO extends IGenericDAO<Subscribe>{
    Subscribe readByOwnerUsername(String ownerUsername);
}

package twitter.service.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import twitter.beans.Privilege;
import twitter.beans.User;

import java.util.List;

/**
 * Created by moluram on 24.3.17.
 */
@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService{
  public void addUser(User user) {

  }

  public List<User> listUser() {
    return null;
  }

  public void removeUser(Integer id) {

  }

  public User findByName(String name) {
    return null;
  }
}

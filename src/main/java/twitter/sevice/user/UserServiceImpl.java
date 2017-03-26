package twitter.sevice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.beans.User;
import twitter.repository.UserRepository;

import java.util.List;

/**
 * Created by Nikolay on 22.03.2017.
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

  @Override
  public List<User> listUser() {
    return userRepository.findAll();
  }

  @Override
  public void removeUser(Integer id) {
    userRepository.delete(id);
  }
}

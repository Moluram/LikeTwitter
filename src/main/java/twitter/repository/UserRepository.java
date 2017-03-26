package twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitter.beans.User;

/**
 * Represents an interface to work with database
 *
 * @author Nikolay Borsuk
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}

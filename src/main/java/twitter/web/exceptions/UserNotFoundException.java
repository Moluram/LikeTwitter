package twitter.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception represent a case in which user does not exist
 *
 * @author Aliaksei Chorny
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such user")
public class UserNotFoundException extends RuntimeException {}
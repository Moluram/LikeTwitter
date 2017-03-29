package twitter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Moluram on 3/29/2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such user")
public class UserNotFoundException extends RuntimeException {
}

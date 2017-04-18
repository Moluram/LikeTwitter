package twitter.service.security;

import org.springframework.stereotype.Service;

/**
 * Created by Moluram on 3/29/2017.
 */
@Service
public interface SecurityService {
  String validatePasswordResetToken(long id, String token);
}

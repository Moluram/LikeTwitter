package twitter.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import twitter.beans.PasswordResetToken;
import twitter.beans.User;

import java.util.Arrays;
import java.util.Calendar;
import twitter.dao.IPasswordResetDAO;

/**
 * Created by Moluram on 3/29/2017.
 */
@Service
public class SecurityServiceImpl implements SecurityService {
  private IPasswordResetDAO passwordResetRepository;

  @Autowired
  public void setPasswordResetRepository(IPasswordResetDAO passwordResetRepository) {
    this.passwordResetRepository = passwordResetRepository;
  }

  @Override
  public String validatePasswordResetToken(long id, String token) {
    PasswordResetToken passToken = passwordResetRepository.findByToken(token);
    if ((passToken == null) || (passToken.getUser().getId() != id)) {
      return "invalidToken";
    }

    Calendar cal = Calendar.getInstance();
    if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0)) {
      return "expired";
    }

    User user = passToken.getUser();
    Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
        Arrays.asList(
            new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
    SecurityContextHolder.getContext().setAuthentication(auth);
    return null;
  }
}

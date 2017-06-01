package twitter.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.entity.PasswordResetToken;

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
    return null;
  }
}

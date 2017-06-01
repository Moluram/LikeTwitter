package twitter.web.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import twitter.service.login.LoginAttemptService;

/**
 * Establishes that login failed
 *
 * @author berthold
 */
@Component
public class AuthenticationFailureListener
    implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  private final LoginAttemptService loginAttemptService;

  @Autowired
  public AuthenticationFailureListener(LoginAttemptService loginAttemptService) {
    this.loginAttemptService = loginAttemptService;
  }

  @Override
  public void onApplicationEvent(
      AuthenticationFailureBadCredentialsEvent event) {
    WebAuthenticationDetails auth = (WebAuthenticationDetails)
        event.getAuthentication().getDetails();
    loginAttemptService.loginFailed(auth.getRemoteAddress());
  }
}

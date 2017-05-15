package twitter.web.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import twitter.service.login.LoginAttemptService;

/**
 * Establishes that login succeed
 *
 * @author berthold
 */
@Component
public class AuthenticationSuccessEventListener
    implements ApplicationListener<AuthenticationSuccessEvent> {

  private final LoginAttemptService loginAttemptService;

  @Autowired
  public AuthenticationSuccessEventListener(LoginAttemptService loginAttemptService) {
    this.loginAttemptService = loginAttemptService;
  }

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
    WebAuthenticationDetails auth = (WebAuthenticationDetails)
        event.getAuthentication().getDetails();
    loginAttemptService.loginSucceeded(auth.getRemoteAddress());
  }
}

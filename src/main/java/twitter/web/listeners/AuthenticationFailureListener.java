package twitter.web.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import twitter.service.login.LoginAttemptService;

/**
 * Created by berthold on 29.03.2017.
 */
public class AuthenticationFailureListener
    implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Autowired
  private LoginAttemptService loginAttemptService;

  @Override
  public void onApplicationEvent(
      AuthenticationFailureBadCredentialsEvent event) {
    WebAuthenticationDetails auth = (WebAuthenticationDetails)
        event.getAuthentication().getDetails();

    loginAttemptService.loginFailed(auth.getRemoteAddress());

  }
}

package twitter.web.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import twitter.service.login.LoginAttemptService;

/**
 * Created by berthold on 29.03.2017.
 */
public class AuthenticationSuccessEventListener
    implements ApplicationListener<AuthenticationSuccessEvent> {

  @Autowired
  private LoginAttemptService loginAttemptService;

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {

    WebAuthenticationDetails auth = (WebAuthenticationDetails)
        event.getAuthentication().getDetails();

    loginAttemptService.loginSucceeded(auth.getRemoteAddress());
  }
}

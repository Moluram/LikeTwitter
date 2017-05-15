package twitter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import twitter.constants.RolesAndPrivileges;
import twitter.service.MyUserDetailsService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.URLConstants;
import twitter.web.constants.WebConstants;

/**
 * Class configuration to a spring security
 *
 * @author Aliaksei Chorny
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class  SecurityConf extends WebSecurityConfigurerAdapter {

  private MyUserDetailsService userDetailsService;

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Autowired
  public void setUserDetailsService(MyUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf()
        .disable();

    http.authorizeRequests()
        .antMatchers("/resources/**", "/about", "/contact", "/settings/**",
            WebConstants.SLASH + URLConstants.SIGNUP_PAGE + WebConstants.SLASH + "**").permitAll()
        .antMatchers( WebConstants.SLASH + URLConstants.SIGNIN_PAGE,
            WebConstants.SLASH, WebConstants.SLASH + URLConstants.SIGNUP_PAGE).anonymous()
        .antMatchers(WebConstants.SLASH + "**").hasAuthority(RolesAndPrivileges.VIEW_PAGES_PRIVILEGE);

    http.exceptionHandling().accessDeniedPage(WebConstants.SLASH + URLConstants.ACCESS_DENIED);

    http.formLogin()
          .loginPage(WebConstants.SLASH + URLConstants.SIGNIN_PAGE)
          .successForwardUrl(WebConstants.SLASH + URLConstants.SIGNIN_PAGE)
          .failureUrl(WebConstants.SLASH + URLConstants.SIGNIN_PAGE)
          .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl(WebConstants.SLASH + URLConstants.SIGNIN_PAGE)
          .deleteCookies(AttributeNamesConstants.COOKIE_NAME);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

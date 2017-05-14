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
        .antMatchers( "/signin", "/", "/signup").anonymous()
        .antMatchers("/resources/**", "/about", "/contact", "/settings/**", "/signup/**").permitAll()
        .antMatchers("/**").hasAuthority(RolesAndPrivileges.VIEW_PAGES_PRIVILEGE);

    http.exceptionHandling().accessDeniedPage("/accessDenied");

    http.formLogin()
          .loginPage("/signin")
          .successForwardUrl("/signin")
          .failureUrl("/signin?error=true")
          .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/signin").deleteCookies("JSESSIONID");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

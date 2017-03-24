package twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter.beans.Privilege;
import twitter.beans.Role;
import twitter.beans.User;
import twitter.service.role.RoleService;
import twitter.service.user.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by moluram on 24.3.17.
 */
@Service("userDetailService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {
  private UserService userService;

  private RoleService roleService;

  private MessageSource messageSource;

  @Autowired
  @Qualifier("userService")
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  @Qualifier("roleService")
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public UserDetails loadUserByUsername(String username) throws
      UsernameNotFoundException {
    User user = userService.findByName(username);
    if (user == null) {
      throw new UsernameNotFoundException("Username does not exist!");
    }
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), user.isEnabled(),
        true, true,
        true, getAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities
      (Collection<Role> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }

  private Collection<GrantedAuthority> getGrantedAuthorities(
      List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege: privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }

  private List<String> getPrivileges(Collection<Role> roles) {
    List<String> privileges = new ArrayList<>();
    List<Privilege> collection = new ArrayList<>();
    for (Role role: roles) {
      collection.addAll(role.getPrivileges());
    }
    for (Privilege item: collection) {
      privileges.add(item.getName());
    }
    return privileges;
  }
}

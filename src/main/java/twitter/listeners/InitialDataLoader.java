package twitter.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import twitter.beans.Privilege;
import twitter.beans.Role;
import twitter.beans.User;
import twitter.service.privilage.PrivilegeService;
import twitter.service.role.RoleService;
import twitter.service.user.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * Load and creates users privileges and roles
 * @author Aliaksei Chorny
 */
@Component
public class InitialDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {

  private static final String READ_PRIVILEGE = "READ_PRIVILEGE";
  private static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";
  private static final String ROLE_ADMIN = "ROLE_ADMIN";
  private static final String ROLE_USER = "ROLE_USER";
  private boolean alreadySetup = false;

  private UserService userService;

  private RoleService roleService;

  private PrivilegeService privilegeService;

  private PasswordEncoder passwordEncoder;

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
  @Qualifier("privilegeService")
  public void setPrivilegeService(PrivilegeService privilegeService) {
    this.privilegeService = privilegeService;
  }

  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    if (alreadySetup) {
      return;
    }
    Privilege readPrivilege = createPrivilegeIfNotFound(READ_PRIVILEGE);
    Privilege writePrivilege = createPrivilegeIfNotFound(WRITE_PRIVILEGE);
    List<Privilege> userPrivileges = Arrays
        .asList(readPrivilege, writePrivilege);
    createRoleIfNotFound(ROLE_ADMIN, userPrivileges);
    createRoleIfNotFound(ROLE_USER, userPrivileges);

    createUser();

    alreadySetup = true;
  }

  private void createUser() {
    Role adminRole = roleService.findByName(ROLE_ADMIN);
    User user = new User("moluram", passwordEncoder.encode("123"),
        "lala@mail.com", Arrays.asList(adminRole));
    userService.addUser(user);
  }

  @Transactional
  private Privilege createPrivilegeIfNotFound(String privilegeName) {
    Privilege privilege = privilegeService.findByName(privilegeName);
    if (privilege == null) {
      privilege = new Privilege(privilegeName);
      privilegeService.addPrivilege(privilege);
    }
    return privilege;
  }

  @Transactional
  private Role createRoleIfNotFound(
      String name, List<Privilege> privileges) {
    Role role = roleService.findByName(name);
    if (role == null) {
      role = new Role(name, privileges);
      roleService.addRole(role);
    }
    return role;
  }

  public PasswordEncoder getPasswordEncoder() {
    return passwordEncoder;
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}

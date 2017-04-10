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

  private static final String VIEW_PAGES_PRIVILEGE = "VIEW_PAGES_PRIVILEGE";
  private static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";
  private static final String READ_PRIVILEGE = "READ_PRIVILEGE";
  private static final String ROLE_ADMIN = "ROLE_ADMIN";
  private static final String ROLE_USER = "ROLE_USER";
  private boolean alreadySetup = false;

  private RoleService roleService;

  private PrivilegeService privilegeService;

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
    Privilege viewPagesPrivilege = createPrivilegeIfNotFound(VIEW_PAGES_PRIVILEGE);
    List<Privilege> userPrivileges = Arrays
        .asList(readPrivilege, writePrivilege, viewPagesPrivilege);
    createRoleIfNotFound(ROLE_ADMIN, userPrivileges);
    createRoleIfNotFound(ROLE_USER, userPrivileges);
    alreadySetup = true;
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


}

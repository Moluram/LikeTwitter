package twitter.web.controllers;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.Privilege;
import twitter.beans.Role;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.dao.privilege.PrivilegeDAO;
import twitter.dao.role.RoleDAO;
import twitter.dao.user.UserDAO;
import twitter.dao.verificationtoken.VerificationTokenDAO;
import twitter.service.user.UserService;

@Controller
@PreAuthorize("hasRole('IS_AUTHENTICATED_ANONYMOUSLY')")
@RequestMapping("/")
class HomeController {

  private final RoleDAO roleDAO;
  private final PrivilegeDAO privilegeDAO;
  private final UserDAO userDAO;
  private final VerificationTokenDAO verificationTokenDAO;

  @Autowired
  public HomeController(RoleDAO roleDAO, PrivilegeDAO privilegeDAO, UserDAO userDAO,
      VerificationTokenDAO verificationTokenDAO) {
    this.roleDAO = roleDAO;
    this.privilegeDAO = privilegeDAO;
    this.userDAO = userDAO;
    this.verificationTokenDAO = verificationTokenDAO;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView sayHello(ModelAndView model) {
    model.setViewName("welcome");


    User user=userDAO.read(21);
    VerificationToken verificationToken=new VerificationToken(user,"sdfdsfdsfdsfsdfjdsfjsdfjk");

    System.out.println("BY NAME");
    verificationToken=verificationTokenDAO.findByTokenName("sdfdsfdsfdsfsdfjdsfjsdfjk");
    System.out.println(verificationToken);
    System.out.println("USER");
    System.out.println(verificationTokenDAO.getUserByToken("sdfdsfdsfdsfsdfjdsfjsdfjk"));
    System.out.println(verificationToken);
    System.out.println("SAME");
    System.out.println(verificationTokenDAO.read(verificationToken.getId()));

    return model;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @RequestMapping(value = "@{username}", method = RequestMethod.GET)
  public ModelAndView userPage(@PathVariable String username,
      ModelAndView model, UserService service) {
    model.setViewName("userPage");
    return model;
  }
}

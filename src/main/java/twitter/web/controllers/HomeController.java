package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
import twitter.dao.user.UserDAO;
import twitter.dao.user.UserDAOJdbcImpl;
import twitter.service.user.UserService;

@Controller
@PreAuthorize("hasRole('IS_AUTHENTICATED_ANONYMOUSLY')")
@RequestMapping("/")
class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sayHello(ModelAndView model) {
		model.setViewName("welcome");
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

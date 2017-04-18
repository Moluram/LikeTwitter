package twitter.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import twitter.service.user.UserService;

@Controller
@RequestMapping("/")
class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Authentication auth, WebRequest request) {
		return "welcome";
	}

	@RequestMapping(value = "homepage", method = RequestMethod.GET)
	public String homepage() {
		return "homepage";
	}
}

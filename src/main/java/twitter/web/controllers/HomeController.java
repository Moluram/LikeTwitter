package twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter.web.constants.PageNamesConstants;
import twitter.web.constants.WebConstants;

/**
 * Controller is used to get welcome page
 *
 * @author moluram
 */
@Controller
@RequestMapping(WebConstants.SLASH)
class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello() {
		return PageNamesConstants.WELCOME_PAGE;
	}
}

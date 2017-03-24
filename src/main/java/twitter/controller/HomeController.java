package twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sayHello(ModelAndView model) {
		model.setViewName("welcome");
		return model;
	}
}
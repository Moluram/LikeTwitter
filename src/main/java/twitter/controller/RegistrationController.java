package twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import twitter.web.dto.UserDto;

import javax.validation.Valid;
import java.rmi.MarshalException;

/**
 * Serve for control the registration
 * @author Aliaksei Chorny
 */
@Controller
@RequestMapping(value = "/signup")
public class RegistrationController {
  private static final String USER_ATTRIBUTE_NAME = "user";
  private static final String REGISTRATION_PAGE_NAME = "registration";

  @RequestMapping(method = RequestMethod.GET)
  public String showRegistrationForm(WebRequest request, Model model) {
    UserDto userDto = new UserDto();
    model.addAttribute(USER_ATTRIBUTE_NAME, userDto);
    return REGISTRATION_PAGE_NAME;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView registerUserAccount(
      @ModelAttribute(USER_ATTRIBUTE_NAME) @Valid UserDto userDto,
      BindingResult result, WebRequest request, Errors errors) {
    return null;
  }
}

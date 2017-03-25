package twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import twitter.exceptions.EmailExistsException;
import twitter.exceptions.UsernameExistsException;
import twitter.service.user.UserService;
import twitter.web.dto.UserDto;

import javax.validation.Valid;

/**
 * Serve for control the registration
 * @author Aliaksei Chorny
 */
@Controller
@RequestMapping(value = "/signup")
public class RegistrationController {
  private static final String USER_ATTRIBUTE_NAME = "user";
  private static final String REGISTRATION_PAGE_NAME = "registration";

  private UserService userService;

  @Autowired
  @Qualifier("userService")
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

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
    if (!result.hasErrors()) {
      try {
        userService.registerNewUserAccount(userDto);
      } catch (EmailExistsException e) {
        result.rejectValue("email", "message.regError");
      } catch (UsernameExistsException e) {
        result.rejectValue("username", "message.regError");
      }
    }
    if (result.hasErrors()) {
      return new ModelAndView(
          "registration", "user", userDto);
    } else {
      return new ModelAndView(
          "successRegister", "user", userDto);
    }
  }
}

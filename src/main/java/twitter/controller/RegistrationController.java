package twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.events.OnRegistrationCompleteEvent;
import twitter.exceptions.EmailExistsException;
import twitter.exceptions.UsernameExistsException;
import twitter.service.user.UserService;
import twitter.web.dto.UserDto;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

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
  private ApplicationEventPublisher eventPublisher;
  private MessageSource messages;

  @Autowired
  @Qualifier("messageSource")
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @Autowired
  public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

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
    User registered = tryRegisterUser(userDto, result);
    if (result.hasErrors()) {
      return new ModelAndView(
          "registration", "user", userDto);
    } else {
      try {
        trySendMessage(request, registered);
      } catch (Exception me) {
        return new ModelAndView(
            "emailError", "user", userDto);
      }
      return new ModelAndView(
          "successRegister", "user", userDto);
    }
  }

  @RequestMapping(value = "/confirm", method = RequestMethod.GET)
  public String confirmRegistration(WebRequest request, Model model,
                 @RequestParam("token") String token) {
    Locale locale = request.getLocale();

    VerificationToken verificationToken = userService
        .getVerificationToken(token);
    if (verificationToken == null) {
      String message = messages.getMessage("auth.message.invalidToken",
          null, locale);
      model.addAttribute("message", message);
      return "redirect:/badUser.html?lang=" + locale.getLanguage();
    }

    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime
        ()) <= 0) {
      String messageValue = messages.getMessage("auth.message.expired",
          null, locale);
      model.addAttribute("message", messageValue);
      return "redirect:/badUser.html?lang=" + locale.getLanguage();
    }

    user.setEnabled(true);
    userService.saveRegisteredUser(user);

    return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
  }

  private void trySendMessage(WebRequest request, User registered) {
    String appUrl = request.getContextPath();
    eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
        registered, request.getLocale(), appUrl));
  }

  private User tryRegisterUser(UserDto userDto, BindingResult result) {
    User user = new User();
    if (!result.hasErrors()) {
      try {
        user = userService.registerNewUserAccount(userDto);
      } catch (EmailExistsException e) {
        result.rejectValue("email", "message.regError");
      } catch (UsernameExistsException e) {
        result.rejectValue("username", "message.regError");
      }
    }
    return user;
  }
}

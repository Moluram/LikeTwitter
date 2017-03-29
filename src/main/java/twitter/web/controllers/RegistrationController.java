package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
import twitter.beans.VerificationToken;
import twitter.web.beans.GenericResponse;
import twitter.web.events.OnRegistrationCompleteEvent;
import twitter.exceptions.EmailExistsException;
import twitter.exceptions.UsernameExistsException;
import twitter.service.user.UserService;
import twitter.web.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

/**
 * Serve for control the registration
 *
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
  private MailSender mailSender;
  private Environment env;

  @Autowired
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Autowired
  public void setEnvironment(Environment environment) {
    this.env = environment;
  }

  @Autowired
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
    if (result.hasErrors()) {
      return new ModelAndView(
          "registration", "user", userDto);
    } else {
      User registered = tryRegisterUser(userDto, result);
      if (registered == null) {
        return new ModelAndView("registration", "user", userDto);
      }
      try {
        trySendMessage(request, registered);
      } catch (Exception me) {
        return new ModelAndView("emailError", "user", userDto);
      }
      return new ModelAndView("successRegister", "user", userDto);
    }
  }

  @RequestMapping(value = "/resendRegistrationToken", method = RequestMethod.GET)
  @ResponseBody
  public GenericResponse resendRegistrationToken(
      HttpServletRequest request, @RequestParam("token") String existingToken) {
    VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
    User user = userService.getUserByToken(newToken.getToken());
    String appUrl = "http://" + request.getServerName() + ':' + request.getServerPort() + request.getContextPath();
    SimpleMailMessage email = constructResendVerificationToken(appUrl, request.getLocale(), newToken, user);
    mailSender.send(email);

    return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
  }

  private SimpleMailMessage constructResendVerificationToken(String contextPath, Locale locale,
                                                             VerificationToken newToken, User user) {
    String confirmationUrl = contextPath + "/signup/confirm?token=" + newToken.getToken();
    String message = messages.getMessage("message.resendToken", null, locale);
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject("Resend Registration Token");
    email.setText(message + " rn" + confirmationUrl);
    email.setFrom(env.getProperty("support.email"));
    email.setTo(user.getEmail());
    return email;
  }

  @RequestMapping(value = "/confirm", method = RequestMethod.GET)
  public String confirmRegistration(WebRequest request, Model model,
                                    @RequestParam("token") String token) {
    Locale locale = request.getLocale();

    VerificationToken verificationToken = userService.getVerificationToken(token);
    if (verificationToken == null) {
      String message = messages.getMessage("auth.message.invalidToken",
          null, locale);
      model.addAttribute("message", message);
      return "redirect:/badUser.jsp?lang=" + locale.getLanguage();
    }

    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime
        ()) <= 0) {
      String messageValue = messages.getMessage("auth.message.expired",
          null, locale);
      model.addAttribute("message", messageValue);
      model.addAttribute("expired", true);
      model.addAttribute("token", token);
      return "redirect:/badUser.jsp?lang=" + locale.getLanguage();
    }

    user.setEnabled(true);
    userService.saveRegisteredUser(user);
    model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
    return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
  }

  private void trySendMessage(WebRequest request, User registered) {
    String appUrl = request.getContextPath();
    eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
        registered, request.getLocale(), appUrl));
  }

  private User tryRegisterUser(UserDto userDto, BindingResult result) {
    User user = null;
    try {
      user = userService.registerNewUserAccount(userDto);
    } catch (EmailExistsException e) {
      result.rejectValue("email", "message.regError");
    } catch (UsernameExistsException e) {
      result.rejectValue("username", "message.regError");
    }
    return user;
  }
}

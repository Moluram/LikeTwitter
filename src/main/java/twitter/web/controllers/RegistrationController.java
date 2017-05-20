package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import twitter.entity.User;
import twitter.entity.VerificationToken;
import twitter.service.user.UserService;
import twitter.web.constants.*;
import twitter.web.dto.SignUpDto;
import twitter.web.events.OnRegistrationCompleteEvent;
import twitter.web.exceptions.EmailExistsException;
import twitter.web.exceptions.UsernameExistsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

/**
 * Serve for control the registration process
 *
 * @author Aliaksei Chorny
 */
@Controller
@RequestMapping(value = WebConstants.SLASH + URLConstants.SIGNUP_PAGE)
public class RegistrationController {
  private UserService userService;
  private ApplicationEventPublisher eventPublisher;
  private MessageSource messages;
  private MailSender mailSender;

  @Autowired
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
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
  public String showRegistrationForm(Model model) {
    SignUpDto signUpDto = new SignUpDto();
    model.addAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME, signUpDto);
    return PageNamesConstants.SIGNUP_PAGE_NAME;
  }

  @RequestMapping(method = RequestMethod.POST)
  public String registerUserAccount(
      @ModelAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) @Valid SignUpDto signUpDto,
      BindingResult result, HttpServletRequest request, Model model) {
    if (result.hasErrors()) {
      model.addAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME, signUpDto);
      return PageNamesConstants.SIGNUP_PAGE_NAME;
    } else {
      User registered = tryRegisterUser(signUpDto, result);
      if (registered == null) {
        model.addAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME, signUpDto);
        return PageNamesConstants.SIGNUP_PAGE_NAME;
      }
      try {
        trySendMessage(request, registered);
      } catch (Exception me) {
        model.addAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME, signUpDto);
        return WebConstants.REDIRECT + URLConstants.SIGNIN_PAGE;
      }
      model.addAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME , signUpDto);
      return WebConstants.REDIRECT + URLConstants.SIGNIN_PAGE;
    }
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.RESEND_REGISTRATION_TOKEN_PAGE, method = RequestMethod.GET)
  public @ResponseBody Boolean resendRegistrationToken(HttpServletRequest request,
                                                       @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME)
                                                           User sessionUser) {
    VerificationToken newToken = userService.createVerificationToken(sessionUser, UUID.randomUUID
        ().toString());
    String appUrl = WebConstants.HTTP_PART + request.getServerName() + ':' + request.getServerPort()
        + request.getContextPath();
    SimpleMailMessage email = constructResendVerificationToken(appUrl, request.getLocale(), newToken, sessionUser);
    mailSender.send(email);
    return true;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.CONFIRM_REGISTRATION, method = RequestMethod.GET)
  public String confirmRegistration(HttpSession session, Model model,
                                    @RequestParam(AttributeNamesConstants.REGISTRATION_TOKEN) String token) {
    Locale locale = (Locale) session.getAttribute(
        SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    VerificationToken verificationToken = userService.getVerificationToken(token);
    if (verificationToken == null) {
      String message = messages.getMessage(MessagesConstant.INVALID_TOKEN_MESSAGE,
          null, locale);
      model.addAttribute(AttributeNamesConstants.MESSAGE , message);
      return WebConstants.REDIRECT + URLConstants.BAD_USER;
    }

    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime
        ()) <= 0) {
      String messageValue = messages.getMessage(MessagesConstant.AUTH_MESSAGE_EXPIRED ,
          null, locale);
      model.addAttribute(AttributeNamesConstants.MESSAGE , messageValue);
      return WebConstants.REDIRECT + URLConstants.BAD_USER;
    }

    updateSessionIfExist(session);
    user.setEnabled(true);
    userService.saveRegisteredUser(user);
    return WebConstants.REDIRECT + PageNamesConstants.SIGNIN_PAGE;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.TEST_USERNAME, method = RequestMethod.GET)
  public @ResponseBody Boolean testUsername(@RequestParam(AttributeNamesConstants.USERNAME) String username) {
    return userService.getUserByUsername(username) == null;
  }

  private void updateSessionIfExist(HttpSession session) {
    User sessionUser = (User) session.getAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME);
    if (sessionUser != null) {
      sessionUser.setEnabled(true);
      Boolean isEnabled = (Boolean) session.getAttribute(AttributeNamesConstants.IS_ENABLED);
      session.setAttribute(AttributeNamesConstants.IS_ENABLED, isEnabled);
    }
  }

  private SimpleMailMessage constructResendVerificationToken(String contextPath, Locale locale,
                                                             VerificationToken newToken, User user) {
    String confirmationUrl = contextPath + WebConstants.SLASH + URLConstants.SIGNUP_PAGE
        + WebConstants.SLASH + URLConstants.CONFIRM_REGISTRATION
        + "?" + AttributeNamesConstants.REGISTRATION_TOKEN + "=" + newToken.getToken();
    String message = messages.getMessage(MessagesConstant.RESEND_TOKEN, null, locale);
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(messages.getMessage(MessagesConstant.RESEND_TOKEN_TITLE, null, locale));
    email.setText(message + "/r/n" + confirmationUrl);
    email.setTo(user.getEmail());
    return email;
  }

  private void trySendMessage(HttpServletRequest request, User registered) {
    String appUrl = WebConstants.HTTP_PART + request.getServerName() + ':' + request.getServerPort() + request.getContextPath();
    eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
        registered, request.getLocale(), appUrl));
  }

  private User tryRegisterUser(SignUpDto signUpDto, BindingResult result) {
    User user = null;
    try {
      user = userService.registerNewUserAccount(signUpDto);
    } catch (EmailExistsException e) {
      result.rejectValue(AttributeNamesConstants.EMAIL, MessagesConstant.REG_ERROR);
    } catch (UsernameExistsException e) {
      result.rejectValue(AttributeNamesConstants.USERNAME, MessagesConstant.REG_ERROR);
    }
    return user;
  }
}

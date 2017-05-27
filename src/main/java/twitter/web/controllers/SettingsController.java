package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import twitter.entity.User;
import twitter.service.security.SecurityService;
import twitter.service.user.UserService;
import twitter.service.userprofile.UserProfileService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.PageNamesConstants;
import twitter.web.constants.URLConstants;
import twitter.web.constants.WebConstants;
import twitter.web.dto.PasswordDto;
import twitter.web.dto.SettingDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Moluram on 3/29/2017.
 */
@Controller
@RequestMapping("/settings")
public class SettingsController {
  private UserService userService;
  private MailSender mailSender;
  private MessageSource messages;
  private Environment env;
  private SecurityService securityService;
  private UserProfileService userProfile;

  @Autowired
  public void setUserProfile(UserProfileService userProfile) {
    this.userProfile = userProfile;
  }

  @Autowired
  public void setSecurityService(SecurityService securityService) {
    this.securityService = securityService;
  }

  @Autowired
  public void setEnv(Environment env) {
    this.env = env;
  }

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @Autowired
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
  public String resetPassword() {
    return "resetPassword";
  }

  @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
  public @ResponseBody Boolean resetPassword(HttpServletRequest request, @ModelAttribute("username") String username) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      return false;
    }
    String token = UUID.randomUUID().toString();
    userService.createPasswordResetTokenForUser(user, token);
    mailSender.send(constructResetTokenEmail(request, token, user));
    return true;
  }

  @RequestMapping(value =  WebConstants.SLASH + URLConstants.CHANGE_PASSWORD, method = RequestMethod.GET)
  public String changePassword(HttpServletRequest request, @RequestParam("id") long id,
                               @RequestParam("token") String token, Model model,
                               @RequestParam("username") String username) {
    String result = securityService.validatePasswordResetToken(id, token);
    if (result != null) {
      model.addAttribute("message",
          messages.getMessage("auth.message." + result, null, request.getLocale()));
      return "redirect:/signin?lang=" + request.getLocale().getLanguage();
    }
    model.addAttribute("passwords", new PasswordDto(username));
    return "updatePassword";
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.CHANGE_PASSWORD, method = RequestMethod.POST)
  public String changePassword(@ModelAttribute("passwords") @Valid PasswordDto passwordDto, BindingResult result,
      HttpServletRequest request, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("passwords", passwordDto);
      return "updatePassword";
    }
    userService.changeUserPassword(userService.getUserByUsername(passwordDto.getUsername()), passwordDto.getPassword());
    return "redirect:/signin?lang=" + request.getLocale().getLanguage();
  }

  @RequestMapping(method = RequestMethod.GET)
  public String getPage(Model model, @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user) {
    model.addAttribute(AttributeNamesConstants.PROFILE, user.getUserProfile());
    model.addAttribute(AttributeNamesConstants.SETTINGS, Arrays.asList(
        new SettingDto(AttributeNamesConstants.EMAIL, user.getEmail()),
        new SettingDto(AttributeNamesConstants.SETTING_STATUS, user.getUserProfile().getStatus()),
        new SettingDto(AttributeNamesConstants.SETTING_FIRST_NAME, user.getUserProfile().getFirstName()),
        new SettingDto(AttributeNamesConstants.SETTING_LAST_NAME, user.getUserProfile().getLastName())));
    return PageNamesConstants.USER_SETTINGS;
  }

  @RequestMapping(value = WebConstants.SLASH + AttributeNamesConstants.EMAIL, method = RequestMethod.POST)
  public @ResponseBody Boolean setEmail(@RequestParam(AttributeNamesConstants.SETTING_VALUE) String email,
                                           @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user) {
    user.setEmail(email);
    userService.saveRegisteredUser(user);
    return true;
  }

  @RequestMapping(value = WebConstants.SLASH + AttributeNamesConstants.SETTING_STATUS, method = RequestMethod.POST)
  public @ResponseBody Boolean setStatus(@RequestParam(AttributeNamesConstants.SETTING_VALUE) String status,
                                         @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user) {
    user.getUserProfile().setStatus(status);
    userProfile.updateUserProfile(user.getUserProfile());
    return true;
  }

  @RequestMapping(value = WebConstants.SLASH + AttributeNamesConstants.SETTING_FIRST_NAME, method = RequestMethod.POST)
  public @ResponseBody Boolean setFirstName(@RequestParam(AttributeNamesConstants.SETTING_VALUE) String firstName,
                                            @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user) {
    user.getUserProfile().setFirstName(firstName);
    userProfile.updateUserProfile(user.getUserProfile());
    return true;
  }

  @RequestMapping(value = WebConstants.SLASH + AttributeNamesConstants.SETTING_LAST_NAME, method = RequestMethod.POST)
  public @ResponseBody Boolean setLastName(@RequestParam(AttributeNamesConstants.SETTING_VALUE) String lastName,
                                           @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user) {
    user.getUserProfile().setLastName(lastName);
    userProfile.updateUserProfile(user.getUserProfile());
    return true;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.TEST_USERNAME, method = RequestMethod.GET)
  public @ResponseBody Boolean testUsername(@RequestParam(AttributeNamesConstants.USERNAME) String username) {
    return userService.getUserByUsername(username) == null;
  }


  private SimpleMailMessage constructResetTokenEmail(
      HttpServletRequest request, String token, User user) {
    String url = "http://" + request.getServerName() + ':' + request.getServerPort() + request.getContextPath()
        + "/settings/change-password?id=" + user.getId() + "&token=" + token + "&username=" + user.getUsername();
    String message = messages.getMessage("message.resetPassword",null, request.getLocale());
    return constructEmail("Reset Password", message + " \r\n " + url, user);
  }

  private SimpleMailMessage constructEmail(String subject, String body,
                                           User user) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(subject);
    email.setText(body);
    email.setTo(user.getEmail());
    email.setFrom(env.getProperty("support.email"));
    return email;
  }
}

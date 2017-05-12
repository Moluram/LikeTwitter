package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
import twitter.service.security.SecurityService;
import twitter.service.security.SecurityServiceImpl;
import twitter.web.dto.PasswordDto;
import twitter.web.exceptions.UserNotFoundException;
import twitter.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
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
  public @ResponseBody Boolean resetPassword(HttpServletRequest request, @RequestParam("username") String username) {
    String token = UUID.randomUUID().toString();
    User user = userService.getUserByUsername(username);
    userService.createPasswordResetTokenForUser(user, token);
    mailSender.send(constructResetTokenEmail(request, token, user));
    return true;
  }

  @RequestMapping(value = "/change-password", method = RequestMethod.GET)
  public String changePassword(HttpServletRequest request, @RequestParam("id") long id,
                               @RequestParam("token") String token, Model model,
                               @RequestParam("username") String username) {
    String result = securityService.validatePasswordResetToken(id, token);
    if (result != null) {
      model.addAttribute("message",
          messages.getMessage("auth.message." + result, null, request.getLocale()));
      return "redirect:/signin?lang=" + request.getLocale().getLanguage();
    }
    model.addAttribute("passwords", new PasswordDto());
    model.addAttribute("username", username);
    return "updatePassword";
  }

  @RequestMapping(value = "/change-password", method = RequestMethod.POST)
  public String changePassword(HttpServletRequest request, Model model, @RequestParam("username") String username,
                               @RequestParam("passwords") @Valid PasswordDto passwordDto, BindingResult result) {
    if (result.hasErrors()) {
      model.addAttribute("passwords", passwordDto);
      return "updatePassword";
    }
    userService.changeUserPassword(userService.getUserByUsername(username), passwordDto.getPassword());
    return "redirect:/signin?lang=" + request.getLocale().getLanguage();
  }

  private SimpleMailMessage constructResetTokenEmail(
      HttpServletRequest request, String token, User user) {
    String url = "http://" + request.getServerName() + ':' + request.getServerPort() + request.getContextPath()
        + "/settings/change-password?id=" + user.getId() + "&token=" + token + "&username=" + user.getUsername();
    String message = messages.getMessage("message.resetPassword",null, request.getLocale());
    return constructEmail("Reset Password", message + " \r\n" + url, user);
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

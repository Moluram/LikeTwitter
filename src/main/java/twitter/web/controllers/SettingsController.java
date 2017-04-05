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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@PreAuthorize("hasRole('USER, ADMIN')")
@RequestMapping("/${username}/settings")
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

  @PreAuthorize("hasAuthority('CHANGE_PASSWORD')")
  @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
  public String resetPassword(HttpServletRequest request,
                                    @PathVariable("username") String username) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      throw new UserNotFoundException();
    }
    String token = UUID.randomUUID().toString();
    userService.createPasswordResetTokenForUser(user, token);
    mailSender.send(constructResetTokenEmail(request.getContextPath(),
        request.getLocale(), token, user));
    ModelAndView model = new ModelAndView();
    model.addObject("message",  messages.getMessage("auth.message.invalidToken",
        null, request.getLocale()));
    return "redirect:/badUser.jsp?lang=" + request.getLocale().getLanguage();
  }

  @PreAuthorize("hasAuthority('CHANGE_PASSWORD')")
  @RequestMapping(value = "/change-password", method = RequestMethod.GET)
  public String changePassword(HttpServletRequest request, @PathVariable("username") String
      username, @RequestParam("id") long id, @RequestParam("token") String token, Model model) {
    String result = securityService.validatePasswordResetToken(id, token);
    if (result != null) {
      model.addAttribute("message",
          messages.getMessage("auth.message." + result, null, request.getLocale()));
      return "redirect:/signin?lang=" + request.getLocale().getLanguage();
    }
    model.addAttribute("passwords", new PasswordDto());
    return "updatePassword";
  }

  @PreAuthorize("hasAuthority('CHANGE_PASSWORD')")
  @RequestMapping(value = "/change-password", method = RequestMethod.POST)
  public String changePassword(HttpServletRequest request,@PathVariable("username") String username,
                               @RequestParam("passwords") @Valid PasswordDto passwordDto) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    userService.changeUserPassword(user, passwordDto.getPassword());
    return "redirect:/signin?lang=" + request.getLocale().getLanguage();
  }

  private SimpleMailMessage constructResetTokenEmail(
      String contextPath, Locale locale, String token, User user) {
    String url = contextPath + "/" + user.getUsername() + "/change-password?id=" +
        user.getId() + "&token=" + token;
    String message = messages.getMessage("message.resetPassword",
        null, locale);
    return constructEmail("Reset Password", message + " \r\n" + url, user);
  }

  private SimpleMailMessage constructEmail(String subject, String body,
                                           User user) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(subject);
    email.setText(body);
    email.setTo(user.getEmail());
    email.setFrom(env.getProperty("mail.username"));
    return email;
  }
}

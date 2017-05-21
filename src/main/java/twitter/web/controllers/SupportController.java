package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import twitter.web.constants.*;
import twitter.web.dto.ContactDto;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Serve for maintaining support pages
 *
 * @author Aliaksei Chorny
 */
@Controller
public class SupportController {
  private MailSender mailSender;
  private Environment env;
  private MessageSource messages;

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @Autowired
  public void setEnvironment(Environment environment) {
    this.env = environment;
  }

  @Autowired
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.ABOUT, method = RequestMethod.GET)
  public String getAbout() {
    return PageNamesConstants.PAGE_ABOUT;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.CONTACT, method = RequestMethod.GET)
  public String getContact(Model model) {
    model.addAttribute(AttributeNamesConstants.CONTACTS, new ContactDto());
    return PageNamesConstants.CONTACT_PAGE;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.CONTACT, method = RequestMethod.POST)
  public String getContact(@ModelAttribute(AttributeNamesConstants.CONTACTS) @Valid ContactDto contactDto, BindingResult result,
                           Model model, WebRequest webRequest) {
    if (result.hasErrors()) {
      model.addAttribute(AttributeNamesConstants.CONTACTS, contactDto);
      return PageNamesConstants.CONTACT_PAGE;
    }

    mailSender.send(constructResendVerificationToken(contactDto, webRequest.getLocale()));
    return WebConstants.REDIRECT + URLConstants.SIGNIN_PAGE;
  }

  private SimpleMailMessage constructResendVerificationToken(ContactDto contactDto, Locale locale) {
    String message = contactDto.getName() + messages.getMessage(MessagesConstant.SOMEONE_SEND_A_MESSAGE, null, locale)
        + contactDto.getEmail() + "\n\t"
        + messages.getMessage(MessagesConstant.SOMEONE_SEND_A_MESSAGE_PART_2, null, locale) + contactDto.getText();
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(contactDto.getName());
    email.setText(message);
    email.setTo(env.getProperty("mail.username"));
    return email;
  }
}

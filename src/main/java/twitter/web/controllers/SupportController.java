package twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.PageNamesConstants;
import twitter.web.constants.URLConstants;
import twitter.web.constants.WebConstants;
import twitter.web.dto.ContactDto;

import javax.validation.Valid;

/**
 * Serve for maintaining support pages
 *
 * @author Aliaksei Chorny
 */
@Controller
public class SupportController {
  @RequestMapping(value = WebConstants.SLASH + URLConstants.ABOUT, method = RequestMethod.GET)
  public String getAbout() {
    return PageNamesConstants.PAGE_ABOUT;
  }

  @RequestMapping(value =  WebConstants.SLASH + URLConstants.CONTACT, method = RequestMethod.GET)
  public String getContact(Model model) {
    model.addAttribute(AttributeNamesConstants.CONTACTS, new ContactDto());
    return PageNamesConstants.CONTACT_PAGE;
  }

  @RequestMapping(value =  WebConstants.SLASH + URLConstants.CONTACT, method = RequestMethod.POST)
  public String getContact(@ModelAttribute(AttributeNamesConstants.CONTACTS) @Valid ContactDto contactDto, BindingResult result,
                           Model model) {
    if (result.hasErrors()) {
      model.addAttribute(AttributeNamesConstants.CONTACTS, contactDto);
      return PageNamesConstants.CONTACT_PAGE;
    }
    return WebConstants.REDIRECT + URLConstants.SIGNIN_PAGE;
  }
}

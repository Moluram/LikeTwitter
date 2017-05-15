package twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter.web.dto.ContactDto;

import javax.validation.Valid;

/**
 * Created by Moluram on 4/27/2017.
 */
@Controller
public class SupportController {
  @RequestMapping(value = "/about", method = RequestMethod.GET)
  public String getAbout() {
    return "about";
  }

  @RequestMapping(value = "/contact", method = RequestMethod.GET)
  public String getContact(Model model) {
    model.addAttribute("contacts", new ContactDto());
    return "contact";
  }

  @RequestMapping(value = "/contact", method = RequestMethod.POST)
  public String getContact(@ModelAttribute("contacts") @Valid ContactDto contactDto, BindingResult result,
                           Model model) {
    if (result.hasErrors()) {
      model.addAttribute("contats", contactDto);
      return "contact";
    }
    return "redirect:/signin";
  }
}

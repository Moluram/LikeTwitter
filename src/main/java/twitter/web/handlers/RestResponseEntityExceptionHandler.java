package twitter.web.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import twitter.exceptions.UserNotFoundException;
import twitter.web.beans.GenericResponse;

import javax.annotation.Resource;

/**
 * Created by Moluram on 3/28/2017.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  private MessageSource messages;

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  @ExceptionHandler({UserNotFoundException.class})
  public ModelAndView handleUserNotFound(RuntimeException ex, WebRequest request) {
    logger.error("404 Status Code", ex);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("errors/404error");
    return mav;
  }

  @ExceptionHandler({MailAuthenticationException.class})
  public ModelAndView handleMail(RuntimeException ex, WebRequest request) {
    logger.error("500 Status Code", ex);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("badUser");
    mav.addObject("message", messages.getMessage("error.mailnotfound", null,
        request.getLocale()));
    return mav;
  }
}

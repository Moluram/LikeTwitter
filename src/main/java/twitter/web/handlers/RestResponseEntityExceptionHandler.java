package twitter.web.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import twitter.web.exceptions.UserNotFoundException;

/**
 * Serve for reacting on server exceptions
 *
 * @author moluram
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private MessageSource messages;

  @Autowired
  public void setMessages(MessageSource messages) {
    this.messages = messages;
  }

  /**
   * Handles 404 Status Code
   * @param ex - fired exception
   * @return redirected to a page
   */
  @ExceptionHandler({UserNotFoundException.class})
  public ModelAndView handleUserNotFound(RuntimeException ex) {
    logger.error("404 Status Code", ex);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("404error");
    return mav;
  }

  /**
   * Handles 500 Status Code
   * @param ex - fired exception
   * @param request - request in which exception fired
   * @return redirected to a page
   */
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


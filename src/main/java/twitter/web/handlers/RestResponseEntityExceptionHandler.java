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
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import twitter.exceptions.UserNotFoundException;
import twitter.web.beans.GenericResponse;

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
  public ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
    logger.error("404 Status Code", ex);
    GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.userNotFound",
            null, request.getLocale()), "UserNotFound"
    );
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND,
        request);
  }

  @ExceptionHandler({MailAuthenticationException.class})
  public ResponseEntity<Object> handleMail(RuntimeException ex, WebRequest request) {
    logger.error("500 Status Code", ex);
    GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage(
            "message.error", null, request.getLocale()), "InternalError");

    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}

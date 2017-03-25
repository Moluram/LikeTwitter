package twitter.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation to check emails
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
  String message() default "Invalid email";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payLoad() default {};
}

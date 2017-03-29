package twitter.web.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Service serve for give access to the privileges
 * @author Aliaksei Chorny
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
  String message() default "Passwords don't matches";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

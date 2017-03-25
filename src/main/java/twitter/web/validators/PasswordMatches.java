package twitter.web.validators;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Service serve for give access to the privileges
 * @author Aliaksei Chorny
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {}

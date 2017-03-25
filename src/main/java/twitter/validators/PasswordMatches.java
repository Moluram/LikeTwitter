package twitter.validators;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by moluram on 25.3.17.
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {}

package twitter.web.validators;

import twitter.web.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class serve for checking is passwords matches
 * @author Aliaksei Chorny
 */
public class PasswordMatchesValidator
    implements ConstraintValidator<PasswordMatches, Object> {
  @Override
   public void initialize(PasswordMatches constraint) {
   }

   @Override
   public boolean isValid(Object obj, ConstraintValidatorContext context) {
     UserDto userDto = (UserDto) obj;
     return userDto.getPassword().equals(userDto.getMatchingPassword());
   }
}

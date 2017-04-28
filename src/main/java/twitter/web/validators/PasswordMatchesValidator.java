package twitter.web.validators;

import twitter.web.dto.SignUpDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class serve for checking is passwords matches
 * @author Aliaksei Chorny
 */
public class PasswordMatchesValidator
    implements ConstraintValidator<PasswordMatches, SignUpDto> {
  @Override
   public void initialize(PasswordMatches constraint) {
   }

   @Override
   public boolean isValid(SignUpDto user, ConstraintValidatorContext context) {
     SignUpDto signUpDto = user;
     return signUpDto.getPassword().equals(signUpDto.getMatchingPassword());
   }
}

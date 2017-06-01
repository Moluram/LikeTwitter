package twitter.web.validators;

import twitter.web.dto.PasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by moluram on 14.5.17.
 */
public class PasswordMatchesPasswordDto
    implements ConstraintValidator<PasswordMatches, PasswordDto> {
  @Override
  public boolean isValid(PasswordDto passwordDto, ConstraintValidatorContext constraintValidatorContext) {
    return passwordDto.getPassword().equals(passwordDto.getMatchingPassword());
  }
}

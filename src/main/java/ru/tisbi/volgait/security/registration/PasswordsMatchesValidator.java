package ru.tisbi.volgait.security.registration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		RegistrationForm form = (RegistrationForm) value;
		return form.getPassword().equals(form.getMatchingPassword());
	}

}

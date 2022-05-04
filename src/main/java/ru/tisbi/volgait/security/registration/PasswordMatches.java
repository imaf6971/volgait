package ru.tisbi.volgait.security.registration;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordsMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message();

	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
}

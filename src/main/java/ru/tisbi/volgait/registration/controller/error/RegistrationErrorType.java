package ru.tisbi.volgait.registration.controller.error;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;
import ru.tisbi.volgait.registration.exception.PasswordsDoesntMatchException;
import ru.tisbi.volgait.registration.exception.RegistrationException;

public enum RegistrationErrorType {
    PASSWORDS_UNMATCHED(
        new PasswordsDoesntMatchException(),
        new FieldError("user", "matchingPassword", "Пароли не совпадают!")),
    EMAIL_TAKEN(
        new EmailAlreadyTakenException(),
        new FieldError("user", "email", "Пользователь с такой почтой уже существует!"));

    private final RegistrationException cause;
    private final ObjectError objectError;

    RegistrationErrorType(RegistrationException cause, ObjectError objectError) {
        this.cause = cause;
        this.objectError = objectError;
    }

    public static ObjectError getObjectError(RegistrationException e) {
        for (RegistrationErrorType type: RegistrationErrorType.values()) {
            if (type.cause.equals(e)) {
                return type.objectError;
            }
        }
        throw new IllegalArgumentException("Cant find registration error by exception");
    }
}

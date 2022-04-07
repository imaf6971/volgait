package ru.tisbi.volgait.registration.controller.error;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;
import ru.tisbi.volgait.registration.exception.PasswordsDoesntMatchException;

public enum RegistrationErrorType {
    PASSWORDS_UNMATCH(
        new PasswordsDoesntMatchException(),
        new FieldError("user", "matchingPassword", "Пароли не совпадают!")),
    EMAIL_TAKEN(
        new EmailAlreadyTakenException(),
        new FieldError("user", "email", "Пользователь с такой почтой уже существует!"));

    private final Exception cause;
    private final ObjectError objectError;

    RegistrationErrorType(Exception cause, ObjectError objectError) {
        this.cause = cause;
        this.objectError = objectError;
    }

    public static ObjectError getObjectError(Exception e) {
        for (RegistrationErrorType type: RegistrationErrorType.values()) {
            if (type.cause.equals(e)) {
                return type.objectError;
            }
        }
        throw new IllegalArgumentException("Cant find registration error by exception");
    }
}

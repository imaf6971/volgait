package ru.tisbi.volgait.registration.service.impl;

import org.springframework.stereotype.Service;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.exception.PasswordsDoesntMatchException;
import ru.tisbi.volgait.registration.exception.RegistrationException;
import ru.tisbi.volgait.registration.service.RegistrationValidator;

@Service
public class PasswordsMatchValidator implements RegistrationValidator {

    @Override
    public void validate(UserDto userToValidate) throws RegistrationException {
        if (!userToValidate.isPasswordsMatch()) {
            throw new PasswordsDoesntMatchException("UserDto passwords doesnt match!");
        }
    }
}

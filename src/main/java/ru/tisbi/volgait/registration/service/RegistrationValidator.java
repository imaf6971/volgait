package ru.tisbi.volgait.registration.service;

import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.exception.RegistrationException;

public interface RegistrationValidator {

    void validate(UserDto userToValidate) throws RegistrationException;

}

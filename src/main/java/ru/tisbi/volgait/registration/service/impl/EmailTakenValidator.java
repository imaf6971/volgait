package ru.tisbi.volgait.registration.service.impl;

import org.springframework.stereotype.Service;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;
import ru.tisbi.volgait.registration.exception.RegistrationException;
import ru.tisbi.volgait.registration.repository.UserRepository;
import ru.tisbi.volgait.registration.service.RegistrationValidator;

@Service
public class EmailTakenValidator implements RegistrationValidator {

    private final UserRepository userRepository;

    public EmailTakenValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UserDto userToValidate) throws RegistrationException {
        if (userRepository.existsByEmail(userToValidate.getEmail())) {
            throw new EmailAlreadyTakenException("User with email " + userToValidate.getEmail() + " is already present");
        }
    }
}

package ru.tisbi.volgait.registration.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tisbi.volgait.registration.domain.User;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.repository.UserRepository;
import ru.tisbi.volgait.registration.service.RegistrationService;
import ru.tisbi.volgait.registration.service.RegistrationValidator;

import java.util.List;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final List<RegistrationValidator> registrationValidators;

    public RegistrationServiceImpl(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   List<RegistrationValidator> registrationValidators) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.registrationValidators = registrationValidators;
    }

    @Override
    @Transactional
    public void register(UserDto user) {
        validate(user);
        registerNewUser(user);
    }

    private void validate(UserDto user) {
        for (var validator: registrationValidators) {
            validator.validate(user);
        }
    }

    private void registerNewUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}

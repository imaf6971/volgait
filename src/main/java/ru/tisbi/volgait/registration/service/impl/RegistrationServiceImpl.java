package ru.tisbi.volgait.registration.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tisbi.volgait.registration.domain.User;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.repository.UserRepository;
import ru.tisbi.volgait.registration.service.RegistrationService;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailTakenValidator emailTakenValidator;

    public RegistrationServiceImpl(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   EmailTakenValidator emailTakenValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailTakenValidator = emailTakenValidator;
    }

    @Override
    @Transactional
    public void register(UserDto user) {
        emailTakenValidator.validate(user);
        registerNewUser(user);
    }

    private void registerNewUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}

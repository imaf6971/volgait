package ru.tisbi.volgait.registration.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.domain.User;
import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;
import ru.tisbi.volgait.registration.exception.PasswordsDoesntMatchException;
import ru.tisbi.volgait.registration.repository.UserRepository;
import ru.tisbi.volgait.registration.service.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto user) {
        if (!user.isPasswordsMatch()) {
            throw new PasswordsDoesntMatchException("Passwords doesnt match!");
        }
        if (emailAlreadyTaken(user.getEmail())) {
            throw new EmailAlreadyTakenException("Email " + user.getEmail() + " is already taken!");
        }
        registerNewUser(user);
    }

    private boolean emailAlreadyTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private void registerNewUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}

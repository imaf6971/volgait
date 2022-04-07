package ru.tisbi.volgait.registration.service.impl;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.tisbi.volgait.controller.UserDto;
import ru.tisbi.volgait.registration.domain.User;
import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;
import ru.tisbi.volgait.registration.exception.PasswordsDoesntMatchException;
import ru.tisbi.volgait.registration.repository.UserRepository;
import ru.tisbi.volgait.registration.service.UserService;

import static org.springframework.security.core.userdetails.User.withUsername;

public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto user) {
        if (user.isPasswordsMatch() == false) {
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
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found!"));
        return withUsername(user.getEmail())
                .password(user.getPassword())
                .passwordEncoder(passwordEncoder::encode)
                .authorities(new ArrayList<>())
                .build();
    }

}

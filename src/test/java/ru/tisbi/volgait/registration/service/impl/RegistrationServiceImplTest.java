package ru.tisbi.volgait.registration.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.tisbi.volgait.registration.RegistrationService;
import ru.tisbi.volgait.registration.RegistrationServiceImpl;
import ru.tisbi.volgait.registration.UserRepository;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.service.RegistrationValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    RegistrationService underTest;

    @BeforeEach
    void setupUnderTest() {
        underTest = new RegistrationServiceImpl(userRepository, passwordEncoder, new ArrayList<>());
    }

    final String CORRECT_PASSWORD = "pass";

    private UserDto user() {
        return new UserDto("user@mail.com", CORRECT_PASSWORD, CORRECT_PASSWORD);
    }

    @Test
    void testServiceIsNotNull() {
        assertNotNull(underTest);
    }

    @Test
    void testCallsPasswordEncoder() {
        underTest.register(user());

        verify(passwordEncoder)
                .encode(any());
    }

    @Test
    void testCallsPasswordEncoderOnlyOnce() {
        // when
        underTest.register(user());

        // then
        verify(passwordEncoder, times(1))
                .encode(any());
    }

    @Test
    void testCallsPasswordEncoderWithCorrectPassword() {
        // when
        underTest.register(user());

        // then
        verify(passwordEncoder)
                .encode(eq(CORRECT_PASSWORD));
    }

    @Test
    void testCallsPasswordEncoderWithCorrectPasswordOnlyOnce() {
        // when
        underTest.register(user());

        // then
        verify(passwordEncoder, times(1))
                .encode(eq(CORRECT_PASSWORD));
    }

    @Test
    void testSavesToRepository() {
        // when
        underTest.register(user());

        // then
        verify(userRepository)
                .save(any());
    }

    @Test
    void testCallsValidator() {
        // setup
        var validator = mock(RegistrationValidator.class);
        var underTest = new RegistrationServiceImpl(userRepository, passwordEncoder, List.of(validator));

        // when
        underTest.register(user());

        // then
        verify(validator)
                .validate(any());
    }

    @Test
    void testCallsValidatorOnlyOnce() {
        // setup
        var validator = mock(RegistrationValidator.class);
        var underTest = new RegistrationServiceImpl(userRepository, passwordEncoder, List.of(validator));

        // when
        underTest.register(user());

        // then
        verify(validator, times(1))
                .validate(any());
    }

    @Test
    void testCallsValidatorWithSameUser() {
        // setup
        var validator = mock(RegistrationValidator.class);
        var underTest = new RegistrationServiceImpl(userRepository, passwordEncoder, List.of(validator));

        // when
        underTest.register(user());

        // then
        verify(validator)
                .validate(eq(user()));
    }

    @Test
    void testCallsValidatorWithSameUserOnlyOnce() {
        // setup
        var validator = mock(RegistrationValidator.class);
        var underTest = new RegistrationServiceImpl(userRepository, passwordEncoder, List.of(validator));

        // when
        underTest.register(user());

        // then
        verify(validator, times(1))
                .validate(eq(user()));
    }
}
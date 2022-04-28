package ru.tisbi.volgait.registration.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.tisbi.volgait.registration.EmailAlreadyTakenException;
import ru.tisbi.volgait.registration.UserRepository;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.exception.RegistrationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailTakenValidatorTest {

    EmailTakenValidator underTest;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUnderTest() {
        underTest = new EmailTakenValidator(userRepository);
    }

    final String SOME_EMAIL = "some@mail.com";

    final String EXISTING_EMAIl = "exists@mail.com";

    UserDto someUser() {
        return new UserDto(SOME_EMAIL, "", "1");
    }

    UserDto existingUser() {
        return new UserDto(EXISTING_EMAIl, "", "");
    }

    @Test
    void testCallsUserRepositoryExistsByEmail() {
        underTest.validate(someUser());

        verify(userRepository)
                .existsByEmail(any());
    }

    @Test
    void testCallsUserRepositoryExistsByEmailOnlyOnce() {
        underTest.validate(someUser());
        verify(userRepository, times(1))
                .existsByEmail(any());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testCallsUserRepositoryExistsByEmailWithRightArgument() {
        underTest.validate(someUser());
        verify(userRepository)
                .existsByEmail(SOME_EMAIL);
    }

    @Test
    void testThrowsEmailAlreadyTakenExceptionWhenUserIsExists() {
        when(userRepository.existsByEmail(eq(EXISTING_EMAIl)))
                .thenReturn(true);

        assertThrows(EmailAlreadyTakenException.class, () -> underTest.validate(existingUser()));
    }

    @Test
    void testDoesntThrowExceptionWhenUserDoesntExists() {
        when(userRepository.existsByEmail(any()))
                .thenReturn(false);
        assertDoesNotThrow(() -> underTest.validate(someUser()));
    }
}
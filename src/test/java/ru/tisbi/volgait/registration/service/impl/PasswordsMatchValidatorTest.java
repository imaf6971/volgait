package ru.tisbi.volgait.registration.service.impl;

import org.junit.jupiter.api.Test;

import ru.tisbi.volgait.security.registration.domain.UserDto;
import ru.tisbi.volgait.security.registration.exception.PasswordsDoesntMatchException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordsMatchValidatorTest {

    final UserDto VALID_USER = new UserDto("johndoe@mail.com", "pass", "pass");

    final UserDto INVALID_USER = new UserDto("johnny@mail.com", "pass", "pas1");

    @Test
    void testDontThrowExceptionOnValidUser() {
        var underTest = new PasswordsMatchValidator();
        assertDoesNotThrow(() -> underTest.validate(VALID_USER));
    }

    @Test
    void testThrowsExceptionOnInvalidUser() {
        var underTest = new PasswordsMatchValidator();
        assertThrows(PasswordsDoesntMatchException.class, () ->
                underTest.validate(INVALID_USER));
    }
}
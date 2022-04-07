package ru.tisbi.volgait.registration.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.tisbi.volgait.registration.domain.RegistrationForm;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;
import ru.tisbi.volgait.registration.exception.PasswordsDoesntMatchException;
import ru.tisbi.volgait.registration.service.RegistrationService;

import java.lang.ref.ReferenceQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private RegistrationService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    private RegistrationController underTest;

    @BeforeEach
    void underTest() {
        this.underTest = new RegistrationController(userService);
    }

    @Test
    void testControllerIsNotNull() {
        assertNotNull(underTest);
    }

    @Test
    void testRegistrationControllerViewIsRegistration() {
        var view = underTest.showRegistrationForm(model);
        assertEquals(view, "registration");
    }

    @Test
    void testCallsAddAttribute() {
        underTest.showRegistrationForm(model);
        verify(model)
                .addAttribute(any(), any());
    }

    @Test
    void testCallsAddAttributeOnlyOneTime() {
        underTest.showRegistrationForm(model);
        verify(model, times(1))
                .addAttribute(any(), any());
    }

    @Test
    void testCallsAddAttributeWithRightAttributeName() {
        underTest.showRegistrationForm(model);
        verify(model)
                .addAttribute(eq("user"), any());
    }

    @Test
    void testCallsAddAttributeWithNewUser() {
        underTest.showRegistrationForm(model);
        verify(model)
                .addAttribute(any(), eq(new RegistrationForm()));
    }

    @Test
    void testCallsHasErrors() {
        underTest.register(new RegistrationForm(), bindingResult);
        verify(bindingResult)
                .hasErrors();
    }

    @Test
    void testCallsHasErrorsOnlyOnce() {
        underTest.register(new RegistrationForm(), bindingResult);
        verify(bindingResult, times(1))
                .hasErrors();
    }

    @Test
    void testCallsServiceForRegistration() {
        underTest.register(new RegistrationForm(), bindingResult);
        verify(userService)
                .register(any());
    }

    @Test
    void testCallsServiceForRegistrationOnlyOnce() {
        underTest.register(new RegistrationForm(), bindingResult);
        verify(userService, times(1))
                .register(any());
    }

    @Test
    void testCallsServiceRegisterWithRightArguments() {
        RegistrationForm arg = new RegistrationForm("johndoe@mail.com", "password", "password");

        underTest.register(arg, bindingResult);
        verify(userService)
                .register(eq(new UserDto("johndoe@mail.com", "password", "password")));
    }

    @Test
    void testWhenEmailAlreadyExistsThenObjectErrorWasAdded() {
        doThrow(new EmailAlreadyTakenException())
                .when(userService)
                .register(any());

        underTest.register(new RegistrationForm(), bindingResult);
        verify(bindingResult)
                .addError(any());
    }

    @Test
    void testWhenEmailAlreadyExistsThenRightObjectErrorWasAdded() {
        doThrow(new EmailAlreadyTakenException())
                .when(userService)
                .register(any());

        underTest.register(new RegistrationForm(), bindingResult);
        verify(bindingResult)
                .addError(eq(new FieldError("user", "email", "Пользователь с такой почтой уже существует!")));
    }

    @Test
    void testWhenPasswordsUnmatchedThenObjectErrorWasAdded() {
        doThrow(new PasswordsDoesntMatchException())
                .when(userService)
                .register(any());

        underTest.register(new RegistrationForm(), bindingResult);
        verify(bindingResult)
                .addError(any());
    }

    @Test
    void testWhenPasswordsUnmatchedThenRightObjectErrorWasAdded() {
        doThrow(new PasswordsDoesntMatchException())
                .when(userService)
                .register(any());

        underTest.register(new RegistrationForm(), bindingResult);
        verify(bindingResult)
                .addError(eq(new FieldError("user", "matchingPassword", "Пароли не совпадают!")));
    }
}
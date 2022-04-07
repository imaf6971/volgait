package ru.tisbi.volgait.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.tisbi.volgait.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private UserDto formResult;

    private RegistrationController underTest = new RegistrationController(userService);

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
                .addAttribute(any(), eq(new UserDto()));
    }

    @Test
    void testCallsHasErrors() {
        underTest.register(formResult, bindingResult);
        verify(bindingResult)
                .hasErrors();
    }

    @Test
    void testCallsHasErrorsOnlyOnce() {
        underTest.register(formResult, bindingResult);
        verify(bindingResult, times(1))
                .hasErrors();
    }

    @Test
    void testCallsIsPasswordMatch() {
        underTest.register(formResult, bindingResult);
        verify(formResult)
                .isPasswordsMatch();
    }

    @Test
    void testCallsIsPasswordMatchOnlyOnce() {
        underTest.register(formResult, bindingResult);
        verify(formResult, times(1))
                .isPasswordsMatch();
    }
}
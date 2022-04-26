package ru.tisbi.volgait.registration.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tisbi.volgait.registration.controller.error.RegistrationErrorType;
import ru.tisbi.volgait.registration.domain.RegistrationForm;
import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.exception.RegistrationException;
import ru.tisbi.volgait.registration.service.RegistrationService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(
            @ModelAttribute("user") @Valid RegistrationForm form,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            registrationService.register(convertToDto(form));
        } catch (RegistrationException e) {
            return errorView(e, bindingResult);
        }
        authenticateNewUser(form);
        return "redirect:/";
    }

    private String errorView(RegistrationException e, BindingResult bindingResult) {
        bindingResult.addError(RegistrationErrorType.getObjectError(e));
        return "registration";
    }

    private void authenticateNewUser(RegistrationForm form) {
        Authentication auth = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private UserDto convertToDto(RegistrationForm form) {
        return new UserDto(form.getEmail(), form.getPassword(), form.getMatchingPassword());
    }
}

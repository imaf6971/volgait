package ru.tisbi.volgait.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tisbi.volgait.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(
            @ModelAttribute("user")
            @Valid UserDto user,
            BindingResult bindingResult) {
        log.info("Register a user...");
        if (bindingResult.hasErrors()) {
            log.error("Validation error!");
            return "registration";
        }
        if (!user.isPasswordsMatch()) {
            log.error("Passwords doesnt match!");
            bindingResult.addError(passwordsDoesntMatch());
            return "registration";
        }
        if (userService.emailAlreadyTaken(user.getEmail())) {
            log.error("Email already taken!");
            bindingResult.addError(emailAlreadyTaken());
            return "registration";
        }
        userService.register(user);
        return "index";
    }

    private ObjectError emailAlreadyTaken() {
        return new FieldError("user", "email", "Пользователь с такой почтой уже существует!");
    }

    private ObjectError passwordsDoesntMatch() {
        return new FieldError("user", "matchingPassword", "Пароли не совпадают!");
    }
}

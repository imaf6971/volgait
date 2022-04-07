package ru.tisbi.volgait.controller;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @Email(message = "Введите валидный email!")
    @NotBlank(message = "Email не должен быть пустым!")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым!")
    private String password;

    @NotBlank(message = "Пароль не должен быть пустым!")
    private String matchingPassword;

    public boolean isPasswordsMatch() {
        return password.equals(matchingPassword);
    }
}

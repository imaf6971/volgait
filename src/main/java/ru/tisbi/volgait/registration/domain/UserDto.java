package ru.tisbi.volgait.registration.domain;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDto {
    @Email(message = "Введите валидный email!")
    @NotBlank(message = "Email не должен быть пустым!")
    private final String email;

    @NotBlank(message = "Пароль не должен быть пустым!")
    private final String password;

    @NotBlank(message = "Пароль не должен быть пустым!")
    private final String matchingPassword;

    public UserDto() {
        email = "";
        password = "";
        matchingPassword = "";
    }

    public UserDto(String email, String password, String matchingPassword) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public boolean isPasswordsMatch() {
        return password.equals(matchingPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(matchingPassword, userDto.matchingPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, matchingPassword);
    }
}

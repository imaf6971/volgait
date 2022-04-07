package ru.tisbi.volgait.registration.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@EqualsAndHashCode
public class UserDto {

    private final String email;

    private final String password;

    private final String matchingPassword;

    public UserDto(String email, String password, String matchingPassword) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }


}

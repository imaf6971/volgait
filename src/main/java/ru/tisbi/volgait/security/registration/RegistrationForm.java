package ru.tisbi.volgait.security.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegistrationForm {
    
	@Email(message = "Введите валидный email!")
    @NotBlank(message = "Email не должен быть пустым!")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым!")
    private String password;

    @NotBlank(message = "Повторите пароль!")
    private String matchingPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
    
}

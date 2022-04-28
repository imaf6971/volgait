package ru.tisbi.volgait.security.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ru.tisbi.volgait.model.AbstractEntity;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

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
    
}

package ru.tisbi.volgait.security.registration;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.tisbi.volgait.security.user.User;
import ru.tisbi.volgait.security.user.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void register(RegistrationForm form) {
		checkUserExists(form);
		registerNewUser(form);
	}

	private void checkUserExists(RegistrationForm form) {
		if (userExists(form.getEmail())) {
			throw new EmailAlreadyTakenException("User with email " + form.getEmail() + " already exists!");
		}
	}

	private User registerNewUser(RegistrationForm form) {
		var user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		return userRepository.saveAndFlush(user);
	}

	private boolean userExists(String email) {
		return userRepository.existsByEmail(email);
	}

}

package ru.tisbi.volgait.security.registration;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	@Transactional
	public void register(RegistrationForm form) {
		if (userExists(form.getEmail())) {
			throw new EmailAlreadyTakenException("User with email " + form.getEmail() + " already exists!");
		}
		registerUser(form);
	}

	private void registerUser(RegistrationForm form) {
		var user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
	}

	private boolean userExists(String email) {
		return userRepository.existsByEmail(email);
	}
	
}

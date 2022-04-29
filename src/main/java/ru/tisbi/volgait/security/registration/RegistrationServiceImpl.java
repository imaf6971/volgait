package ru.tisbi.volgait.security.registration;

import javax.transaction.Transactional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.tisbi.volgait.security.core.User;
import ru.tisbi.volgait.security.core.UserRepository;

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
		checkUserExists(form);
		var user = registerNewUser(form);
		authenticateNewUser(user);
	}

	private void checkUserExists(RegistrationForm form) {
		if (userExists(form.getEmail())) {
			throw new EmailAlreadyTakenException("User with email " + form.getEmail() + " already exists!");
		}
	}

	private void authenticateNewUser(User user) {
		Authentication token
			= new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(token);
	}
	
	private User registerNewUser(RegistrationForm form) {
		var user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		return userRepository.save(user);
	}

	private boolean userExists(String email) {
		return userRepository.existsByEmail(email);
	}
	
}

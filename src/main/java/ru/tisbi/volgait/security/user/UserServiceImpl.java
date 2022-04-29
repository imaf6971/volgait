package ru.tisbi.volgait.security.user;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceImpl {
	
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getCurrentUser() {
		var securityContext = SecurityContextHolder.getContext();
		var authentication = securityContext.getAuthentication();
		String authenticatedUserEmail = authentication.getName();
		return userRepository.findByEmail(authenticatedUserEmail)
				.orElseThrow(() -> new UserNotFoundException("User " + authenticatedUserEmail + " not found"));
	}
}

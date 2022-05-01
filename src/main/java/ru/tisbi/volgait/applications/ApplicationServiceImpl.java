package ru.tisbi.volgait.applications;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ru.tisbi.volgait.security.user.UserService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationRepository applicationRepository;

	private final UserService userService;

	public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserService userService) {
		this.applicationRepository = applicationRepository;
		this.userService = userService;
	}

	@Override
	public Long countUserApplications(String email) {
		return applicationRepository.countByCreatedBy_Email(email);
	}

	@Override
	public List<Application> findApplicationsByEmail(String name) {
		return applicationRepository.findByCreatedBy_email(name);
	}

	@Override
	public void addApplication(ApplicationForm form) {
		var application = new Application(form.getUuid(), form.getTitle(), userService.getCurrentUser());
		applicationRepository.save(application);
	}

	@Override
	public Application getApplicationByUuid(UUID uuid) {
		return applicationRepository.findByUuid(uuid)
				.orElseThrow(() -> new ApplicationNotFoundException("Application " + uuid + " not found"));
	}

}

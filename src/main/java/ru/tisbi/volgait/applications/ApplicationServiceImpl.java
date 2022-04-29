package ru.tisbi.volgait.applications;

import java.util.List;

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
		var application = new Application();
		application.setTitle(form.getTitle());
		application.setUuid(form.getUuid());
		application.setCreatedBy(userService.getCurrentUser());
		applicationRepository.save(application);
	}
	
}

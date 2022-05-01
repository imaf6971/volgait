package ru.tisbi.volgait.applications;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {

	Long countUserApplications(String email);

	List<Application> findApplicationsByEmail(String name);

	void addApplication(ApplicationForm form);

	Application getApplicationByUuid(UUID uuid);
}

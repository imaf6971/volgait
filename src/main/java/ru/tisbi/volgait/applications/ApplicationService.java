package ru.tisbi.volgait.applications;

import java.util.List;

public interface ApplicationService {

    Long countUserApplications(String email);

	List<Application> findApplicationsByEmail(String name);
}

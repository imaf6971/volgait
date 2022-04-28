package ru.tisbi.volgait.applications;

import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Long countUserApplications(String email) {
        return applicationRepository.countByCreatedBy_Email(email);
    }
}

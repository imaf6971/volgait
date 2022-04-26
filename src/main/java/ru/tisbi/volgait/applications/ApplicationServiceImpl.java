package ru.tisbi.volgait.applications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tisbi.volgait.registration.repository.UserRepository;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long countUserApplications(String email) {
        return applicationRepository.countByCreatedBy_Email(email);
    }
}

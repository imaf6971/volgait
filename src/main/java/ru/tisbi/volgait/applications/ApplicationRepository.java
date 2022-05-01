package ru.tisbi.volgait.applications;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	long countByCreatedBy_Email(String email);

	List<Application> findByCreatedBy_email(String name);

	Optional<Application> findByUuid(UUID applicationUuid);
}

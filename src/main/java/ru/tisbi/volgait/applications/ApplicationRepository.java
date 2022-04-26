package ru.tisbi.volgait.applications;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    long countByCreatedBy_Email(String email);
}

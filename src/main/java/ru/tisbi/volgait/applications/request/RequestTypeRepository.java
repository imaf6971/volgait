package ru.tisbi.volgait.applications.request;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {

	Optional<RequestType> findByTitle(String title);

}

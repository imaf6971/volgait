package ru.tisbi.volgait.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tisbi.volgait.registration.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
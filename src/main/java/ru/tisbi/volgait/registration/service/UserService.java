package ru.tisbi.volgait.registration.service;

import ru.tisbi.volgait.registration.domain.UserDto;
import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;

public interface UserService {

    /**
     * Register a new user
     * @param user to be registered
     * @throws EmailAlreadyTakenException if user with user.getEmail() is present
     */
    void register(UserDto user);
}

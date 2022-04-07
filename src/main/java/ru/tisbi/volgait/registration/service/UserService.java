package ru.tisbi.volgait.registration.service;

import ru.tisbi.volgait.controller.UserDto;
import ru.tisbi.volgait.registration.exception.EmailAlreadyTakenException;

public interface UserService {

    /**
     * Register a new user
     * @param user
     * @throws EmailAlreadyTakenException if user with user.getEmail() is present
     */
    void register(UserDto user);
}

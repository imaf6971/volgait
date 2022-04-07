package ru.tisbi.volgait.service;

import ru.tisbi.volgait.controller.UserDto;

public interface UserService {

    /**
     * Checks if a user with email exists
     * @param email Email
     * @return
     */
    boolean emailAlreadyTaken(String email);


    /**
     * Register a new user
     * @param user
     */
    void register(UserDto user);
}

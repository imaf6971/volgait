package ru.tisbi.volgait.registration;

public interface RegistrationService {

    /**
     * Register a new user
     * @param form of user to be registered
     * @throws EmailAlreadyTakenException if user with form email is present
     */
    void register(RegistrationForm form);
}

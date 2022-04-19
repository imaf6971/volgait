package ru.tisbi.volgait.registration.domain;

public class UserDto {

    private final String email;

    private final String password;

    private final String matchingPassword;

    public UserDto(String email, String password, String matchingPassword) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDto other = (UserDto) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (matchingPassword == null) {
            if (other.matchingPassword != null)
                return false;
        } else if (!matchingPassword.equals(other.matchingPassword))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((matchingPassword == null) ? 0 : matchingPassword.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

}

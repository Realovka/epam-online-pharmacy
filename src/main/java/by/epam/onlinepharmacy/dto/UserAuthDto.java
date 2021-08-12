package by.epam.onlinepharmacy.dto;

import by.epam.onlinepharmacy.entity.Role;

public class UserAuthDto {
    private String login;
    private String password;
    private Role role;

    public UserAuthDto(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserAuthDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

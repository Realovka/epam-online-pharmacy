package by.epam.onlinepharmacy.dto;

import by.epam.onlinepharmacy.entity.Status;

public class UserViewDto {
    private long userId;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private Status status;

    public UserViewDto(long userId, String login, String firstName,
                       String lastName, String email, String telephone, Status status) {
        this.userId = userId;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
    }

    public UserViewDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Builder {
        private UserViewDto newUser;

        public Builder() {
            this.newUser = new UserViewDto();
        }

        public UserViewDto.Builder setUserId(long userId) {
            newUser.userId = userId;
            return this;
        }
        public UserViewDto.Builder setLogin(String login) {
            newUser.login = login;
            return this;
        }

        public UserViewDto.Builder setFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public UserViewDto.Builder setLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public UserViewDto.Builder setTelephone(String telephone) {
            newUser.telephone = telephone;
            return this;
        }

        public UserViewDto.Builder setEmail(String email) {
            newUser.email = email;
            return this;
        }

        public UserViewDto.Builder setStatus(Status status) {
            newUser.status = status;
            return this;
        }

        public UserViewDto build() {
            return newUser;
        }
    }
}

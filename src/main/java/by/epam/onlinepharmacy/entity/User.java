package by.epam.onlinepharmacy.entity;

import java.util.Objects;

public class User {
    private long userId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private Role role;
    private Status status;


    public User() {
    }

    public User(long userId, String login, String password, String firstName,
                String lastName, String email, String telephone, Role role, Status status) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.status = status;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email)
                && Objects.equals(telephone, user.telephone) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result *= 31 + (login != null ? login.hashCode() : 0);
        result *= 31 + (password != null ? password.hashCode() : 0);
        result *= 31 + (firstName != null ? firstName.hashCode() : 0);
        result *= 31 + (lastName != null ? lastName.hashCode() : 0);
        result *= 31 + (email != null ? email.hashCode() : 0);
        result *= 31 + (telephone != null ? telephone.hashCode() : 0);
        result *= 31 + (status != null ? status.hashCode() : 0);
        result *= 31 + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User{");
        builder.append("userId=").append(userId);
        builder.append(", login=").append(login);
        builder.append(", password=").append(password);
        builder.append(", firstName=").append(firstName);
        builder.append(", lastName=").append(lastName);
        builder.append(", email=").append(email);
        builder.append(", telephone=").append(telephone);
        builder.append(", role=").append(role);
        builder.append(", status=").append(status);
        builder.append("}");
        return builder.toString();
    }

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder setUserId(long userId) {
            newUser.userId = userId;
            return this;
        }

        public Builder setLogin(String login) {
            newUser.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            newUser.password = password;
            return this;
        }


        public Builder setFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder setTelephone(String telephone) {
            newUser.telephone = telephone;
            return this;
        }

        public Builder setStatus(Status status) {
            newUser.status = status;
            return this;
        }

        public Builder setRole(Role role) {
            newUser.role = role;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}

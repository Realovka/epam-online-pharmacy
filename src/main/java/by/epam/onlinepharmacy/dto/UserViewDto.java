package by.epam.onlinepharmacy.dto;

import by.epam.onlinepharmacy.entity.Status;

public class UserViewDto {
    private long userId;
    private String firstName;
    private String lastName;
    private Status status;

    public UserViewDto(long userId, String firstName, String lastName, Status status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
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


        public UserViewDto.Builder setFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public UserViewDto.Builder setLastName(String lastName) {
            newUser.lastName = lastName;
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

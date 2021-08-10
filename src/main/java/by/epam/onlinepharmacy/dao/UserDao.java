package by.epam.onlinepharmacy.dao;

import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    void createUser(User user) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
}

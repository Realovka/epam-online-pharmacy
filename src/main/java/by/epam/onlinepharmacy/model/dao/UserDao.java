package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void createUser(User user) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
    Optional<User> authenticationUser(User user) throws DaoException;
    List<User> findAllPharmacists() throws DaoException;
    void changePharmacistStatus(long id, Status status) throws DaoException;
    List<User> findInactivePharmacists() throws DaoException;
}

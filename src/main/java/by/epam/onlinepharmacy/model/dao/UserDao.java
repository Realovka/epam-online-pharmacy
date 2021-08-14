package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Pharmacy;
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
    void updateLogin(long id, String login) throws DaoException;
    void updateFirstName(long id, String firstName) throws DaoException;
    void updateLastName(long id, String lastName) throws DaoException;
    void updateEmail(long id, String email) throws DaoException;
    void updateTelephone(long id, String telephone) throws DaoException;
}

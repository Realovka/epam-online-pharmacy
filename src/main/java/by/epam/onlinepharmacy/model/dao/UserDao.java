package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    int createUser(User user) throws DaoException;
    void createCodeActivation(long userId, String code) throws DaoException;
    long findIdForVerificationCustomer(String code) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
    Optional<User> findById(long id) throws DaoException;
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    List<User> findAllPharmacists() throws DaoException;
    int updateUserStatus(long id, Status status) throws DaoException;
    List<User> findInactivePharmacists() throws DaoException;
    int updateLogin(long id, String login) throws DaoException;
    int updateFirstName(long id, String firstName) throws DaoException;
    int updateLastName(long id, String lastName) throws DaoException;
    int updateEmail(long id, String email) throws DaoException;
    int updateTelephone(long id, String telephone) throws DaoException;
}

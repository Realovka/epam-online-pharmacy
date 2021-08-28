package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> createUser(String login, String password, String firstName, String lastName, String email, String telephone, String role) throws ServiceException;
    Optional<User> authenticationUser(String login, String password) throws ServiceException;
    List<User> findAllPharmacists() throws ServiceException;
    boolean updateCustomerStatus(String code) throws ServiceException;
    void updatePharmacistStatus(String id, Status status) throws ServiceException;
    List<User> findInactivePharmacists() throws ServiceException;
    boolean updateLogin(long id, String login) throws ServiceException;
    void updateFirstName(long id, String firstName) throws ServiceException;
    void updateLastName(long id, String lastName) throws ServiceException;
    void updateEmail(long id, String email) throws ServiceException;
    void updateTelephone(long id, String telephone) throws ServiceException;
}

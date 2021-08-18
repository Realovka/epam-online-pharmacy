package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(User userReg) throws ServiceException;
    Optional<User> authenticationUser(User userAuth) throws ServiceException;
    List<User> findAllPharmacists() throws ServiceException;
    void changePharmacistStatus(String id, Status status) throws ServiceException;
    List<User> findInactivePharmacists() throws ServiceException;
    boolean updateLogin(long id, String login) throws ServiceException;
    void updateFirstName(long id, String firstName) throws ServiceException;
}

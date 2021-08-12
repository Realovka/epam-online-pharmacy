package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.dto.UserAuthDto;
import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(UserRegDto userRegDto) throws ServiceException;
    Optional<User> authenticationUser(UserAuthDto userAuthDto) throws ServiceException;
    List<User> findAllPharmacists() throws ServiceException;
    void verifyPharmacist(String id) throws ServiceException;
}

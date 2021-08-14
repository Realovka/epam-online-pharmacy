package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.dto.UserAuthDto;
import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.dto.UserViewDto;
import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(UserRegDto userRegDto) throws ServiceException;
    Optional<User> authenticationUser(UserAuthDto userAuthDto) throws ServiceException;
    List<UserViewDto> findAllPharmacists() throws ServiceException;
    void changePharmacistStatus(String id, Status status) throws ServiceException;
    List<UserViewDto> findInactivePharmacists() throws ServiceException;
    boolean updateLogin(long id, String login) throws ServiceException;
    void updateFirstName(long id, String firstName) throws ServiceException;
}

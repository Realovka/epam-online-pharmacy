package by.epam.onlinepharmacy.service;

import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.exception.ServiceException;

public interface UserService {
    boolean createUser(UserRegDto userRegDto) throws ServiceException;
}

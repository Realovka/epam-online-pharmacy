package by.epam.onlinepharmacy.model.util;

import by.epam.onlinepharmacy.exception.ServiceException;

public interface PasswordEncoder {
    String createPasswordEncoded(String password) throws ServiceException;
}

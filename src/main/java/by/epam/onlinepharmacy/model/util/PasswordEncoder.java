package by.epam.onlinepharmacy.model.util;

import by.epam.onlinepharmacy.exception.ServiceException;

/**
 * The interface Password encoder.
 */
public interface PasswordEncoder {
    /**
     * Create password encoded string.
     *
     * @param password the password
     * @return the string
     * @throws ServiceException the service exception
     */
    String createPasswordEncoded(String password) throws ServiceException;
}

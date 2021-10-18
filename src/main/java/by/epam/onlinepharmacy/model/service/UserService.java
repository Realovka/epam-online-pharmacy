package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Create user optional.
     *
     * @param login     the login
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param telephone the telephone
     * @param role      the role
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> createUser(String login, String password, String firstName, String lastName, String email, String telephone, String role) throws ServiceException;

    /**
     * Authentication user optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> authenticationUser(String login, String password) throws ServiceException;

    /**
     * Is form valid map.
     *
     * @param login     the login
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param telephone the telephone
     * @return the map
     */
    Map<String,String> isFormValid(String login, String password, String firstName, String lastName, String email, String telephone);

    /**
     * Find all pharmacists list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllPharmacists() throws ServiceException;

    /**
     * Update customer status boolean.
     *
     * @param code the code
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateCustomerStatus(String code) throws ServiceException;

    /**
     * Update pharmacist status boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePharmacistStatus(String id, Status status) throws ServiceException;

    /**
     * Find inactive pharmacists list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findInactivePharmacists() throws ServiceException;

    /**
     * Update login boolean.
     *
     * @param id    the id
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateLogin(long id, String login) throws ServiceException;

    /**
     * Update first name boolean.
     *
     * @param id        the id
     * @param firstName the first name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateFirstName(long id, String firstName) throws ServiceException;

    /**
     * Update last name boolean.
     *
     * @param id       the id
     * @param lastName the last name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateLastName(long id, String lastName) throws ServiceException;

    /**
     * Update email boolean.
     *
     * @param id    the id
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateEmail(long id, String email) throws ServiceException;

    /**
     * Update telephone boolean.
     *
     * @param id        the id
     * @param telephone the telephone
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateTelephone(long id, String telephone) throws ServiceException;
}

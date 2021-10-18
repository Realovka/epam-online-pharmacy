package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Create user int.
     *
     * @param user the user
     * @return the int
     * @throws DaoException the dao exception
     */
    int createUser(User user) throws DaoException;

    /**
     * Create code activation.
     *
     * @param userId the user id
     * @param code   the code
     * @throws DaoException the dao exception
     */
    void createCodeActivation(long userId, String code) throws DaoException;

    /**
     * Find id for verification customer long.
     *
     * @param code the code
     * @return the long
     * @throws DaoException the dao exception
     */
    long findIdForVerificationCustomer(String code) throws DaoException;

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findById(long id) throws DaoException;

    /**
     * Find user by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Find all pharmacists list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllPharmacists() throws DaoException;

    /**
     * Update user status int.
     *
     * @param id     the id
     * @param status the status
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateUserStatus(long id, Status status) throws DaoException;

    /**
     * Find inactive pharmacists list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findInactivePharmacists() throws DaoException;

    /**
     * Update login int.
     *
     * @param id    the id
     * @param login the login
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateLogin(long id, String login) throws DaoException;

    /**
     * Update first name int.
     *
     * @param id        the id
     * @param firstName the first name
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateFirstName(long id, String firstName) throws DaoException;

    /**
     * Update last name int.
     *
     * @param id       the id
     * @param lastName the last name
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateLastName(long id, String lastName) throws DaoException;

    /**
     * Update email int.
     *
     * @param id    the id
     * @param email the email
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateEmail(long id, String email) throws DaoException;

    /**
     * Update telephone int.
     *
     * @param id        the id
     * @param telephone the telephone
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateTelephone(long id, String telephone) throws DaoException;
}

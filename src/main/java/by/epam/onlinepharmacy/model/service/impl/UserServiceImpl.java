package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.UserDao;
import by.epam.onlinepharmacy.model.dao.impl.UserDaoImpl;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.util.PasswordEncoder;
import by.epam.onlinepharmacy.model.util.impl.PasswordEncoderImpl;
import by.epam.onlinepharmacy.model.validation.UserValidator;
import by.epam.onlinepharmacy.model.validation.impl.UserValidatorImpl;
import by.epam.onlinepharmacy.model.verification.EmailSending;
import by.epam.onlinepharmacy.model.verification.impl.EmailSendingImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static by.epam.onlinepharmacy.controller.command.RequestParameter.EMAIL;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.FIRST_NAME;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.LAST_NAME;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.LOGIN;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.PASSWORD;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.TELEPHONE;

public class UserServiceImpl implements UserService {
    private Logger logger = LogManager.getLogger();
    private static final String ROLE_CUSTOMER_IN_ENGLISH = "CUSTOMER";
    private static final String ROLE_PHARMACIST_IN_ENGLISH = "PHARMACIST";
    private static final String ROLE_CUSTOMER_IN_RUSSIAN = "КЛИЕНТ";
    private static final String ROLE_PHARMACIST_IN_RUSSIAN = "СОТРУДНИК АПТЕКИ";
    private static final String HEADER_FOR_VERIFICATION_CUSTOMER = "Activation from Alpha Pharmacy";
    private static final String MESSAGE_FOR_VERIFICATION_CUSTOMER = """
            Hello, %s  %s welcome to Alpha Pharmacy. Your activation code is %s
            """;
    private UserDao userDao = UserDaoImpl.getInstance();
    private UserValidator userValidator = UserValidatorImpl.getInstance();
    private EmailSending emailSending = EmailSendingImpl.getInstance();
    private PasswordEncoder passwordEncoder = PasswordEncoderImpl.getInstance();

    private UserServiceImpl() {

    }

    private static UserServiceImpl instance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> createUser(String login, String password,
                                     String firstName, String lastName, String email, String telephone,
                                     String role) throws ServiceException {
        int result;
        User user = new User();
        role = convertRole(role);
        if (role.equals(Role.CUSTOMER.toString()) || role.equals(Role.PHARMACIST.toString())) {
            user.setRole(Role.valueOf(role));
        }
        String encodedPassword = passwordEncoder.createPasswordEncoded(password);

        user.setLogin(login);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setTelephone(telephone);

        try {
            if (userDao.findByLogin(user.getLogin()).isPresent()) {
                return Optional.empty();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createUser(), while find login  ", e);
            throw new ServiceException("DaoException is in method createUser(), while find login ", e);
        }
        try {
            result = userDao.createUser(user);
        } catch (DaoException ex) {
            logger.log(Level.ERROR, "DaoException is in method createUser(), while create user ", ex);
            throw new ServiceException("DaoException is in method createUser(), while create user ", ex);
        }

        if (user.getRole().
                equals(Role.CUSTOMER) && result > 0) {
            try {
                Optional<User> userReg = userDao.findByLogin(user.getLogin());
                if (userReg.isPresent()) {
                    String code = UUID.randomUUID().toString();
                    userDao.createCodeActivation(userReg.get().getUserId(), code);
                    emailSending.sendEmailVerifyCustomer(userReg.get(), code, HEADER_FOR_VERIFICATION_CUSTOMER,
                            MESSAGE_FOR_VERIFICATION_CUSTOMER);
                }
            } catch (DaoException e) {
                logger.log(Level.ERROR, "DaoException is in method createUser(), while send code", e);
                throw new ServiceException("DaoException is in method createUser(), while send code ", e);
            }
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> authenticationUser(String login, String password) throws ServiceException {
        Optional<User> userFromDb;
        String passwordEncoded = passwordEncoder.createPasswordEncoded(password);
        try {
            userFromDb = userDao.findUserByLoginAndPassword(login, passwordEncoded);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method authenticationUser(), while find user by login and password ", e);
            throw new ServiceException("DaoException is in method authenticationUser(),  while find user by login and password ", e);
        }
        if (userFromDb.isEmpty()) {
            userFromDb = Optional.empty();
        }
        return userFromDb;
    }

    @Override
    public Map<String, String> isFormValid(String login, String password, String firstName,
                                           String lastName, String email, String telephone) {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put(LOGIN, login);
        userParameters.put(PASSWORD, password);
        userParameters.put(FIRST_NAME, firstName);
        userParameters.put(LAST_NAME, lastName);
        userParameters.put(EMAIL, email);
        userParameters.put(TELEPHONE, telephone);
        userValidator.isValidForm(userParameters);
        return userParameters;
    }

    @Override
    public List<User> findAllPharmacists() throws ServiceException {
        List<User> pharmacists;
        try {
            pharmacists = userDao.findAllPharmacists();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllPharmacists() ", e);
            throw new ServiceException("DaoException is in method findAllPharmacists() ", e);
        }
        return pharmacists;
    }

    @Override
    public boolean updatePharmacistStatus(String id, Status status) throws ServiceException {
        boolean result;
        try {
            result = userDao.updateUserStatus(Long.parseLong(id), status) > 0;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method  updatePharmacistStatus() ", e);
            throw new ServiceException("DaoException is in method updatePharmacistStatus() ", e);
        }
        return result;
    }

    @Override
    public boolean updateCustomerStatus(String code) throws ServiceException {
        try {
            long id = userDao.findIdForVerificationCustomer(code);
            if (id != 0) {
                userDao.updateUserStatus(id, Status.ACTIVE);
                return true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method changeCustomerStatus() ", e);
            throw new ServiceException("DaoException is in method changeCustomerStatus ", e);
        }
        return false;
    }

    @Override
    public List<User> findInactivePharmacists() throws ServiceException {
        List<User> inactivePharmacists;
        try {
            inactivePharmacists = userDao.findInactivePharmacists();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findInactivePharmacists() ", e);
            throw new ServiceException("DaoException is in method findInactivePharmacists() ", e);
        }
        return inactivePharmacists;
    }

    @Override
    public boolean updateLogin(long id, String login) throws ServiceException {
        try {
            Optional<User> user = userDao.findByLogin(login);
            if (user.isEmpty()) {
                userDao.updateLogin(id, login);
                return true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateLogin() ", e);
            throw new ServiceException("DaoException is in method updateLogin() ", e);
        }
        return false;
    }

    @Override
    public boolean updateFirstName(long id, String firstName) throws ServiceException {
        boolean result;
        try {
            result = userDao.updateFirstName(id, firstName) > 0;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateFirstName() ", e);
            throw new ServiceException("DaoException is in method updateFirstName() ", e);
        }
        return result;
    }

    @Override
    public boolean updateLastName(long id, String lastName) throws ServiceException {
        boolean result;
        try {
            result = userDao.updateLastName(id, lastName) > 0;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateLastName() ", e);
            throw new ServiceException("DaoException is in method updateLastName() ", e);
        }
        return result;
    }

    @Override
    public boolean updateEmail(long id, String email) throws ServiceException {
        boolean result;
        try {
            result = userDao.updateEmail(id, email) > 0;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateEmail() ", e);
            throw new ServiceException("DaoException is in method updateEmail() ", e);
        }
        return result;
    }

    @Override
    public boolean updateTelephone(long id, String telephone) throws ServiceException {
        boolean result;
        try {
            result = userDao.updateTelephone(id, telephone) > 0;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateTelephone() ", e);
            throw new ServiceException("DaoException is in method updateTelephone() ", e);
        }
        return result;
    }

    private String convertRole(String role) {
        switch (role) {
            case ROLE_CUSTOMER_IN_RUSSIAN -> role = ROLE_CUSTOMER_IN_ENGLISH;
            case ROLE_PHARMACIST_IN_RUSSIAN -> role = ROLE_PHARMACIST_IN_ENGLISH;
        }
        return role;
    }
}

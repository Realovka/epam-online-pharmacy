package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.UserDao;
import by.epam.onlinepharmacy.model.dao.impl.UserDaoImpl;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private Logger logger = LogManager.getLogger();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean createUser(User userReg) throws ServiceException {
        try {
            if (userDao.findByLogin(userReg.getLogin()).isPresent()) {
                return false;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Such login already exists ", e);
            throw new ServiceException("Such login already exists ", e);
        }
        userReg.setPassword(PasswordEncoder.createPasswordEncoded(userReg.getPassword()));
        try {
            userDao.createUser(userReg);
        } catch (DaoException ex) {
            logger.log(Level.ERROR, "Exception is in method createUser() ", ex);
            throw new ServiceException("Exception is in method createUser() ", ex);
        }
        return true;
    }

    @Override
    public Optional<User> authenticationUser(User userAuth) throws ServiceException {
        Optional<User> userFromDb;
        String passwordEncoded = PasswordEncoder.createPasswordEncoded(userAuth.getPassword());
        userAuth.setPassword(passwordEncoded);
        try {
           userFromDb = userDao.authenticationUser(userAuth);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method authenticationUser() " , e);
            throw new ServiceException("Exception is in method authenticationUser() ", e);
       }
       if(userFromDb.isEmpty()) {
           userFromDb = Optional.empty();
       }
       return userFromDb;
    }

    @Override
    public List<User> findAllPharmacists() throws ServiceException {
        List<User> pharmacists;
        try {
           pharmacists =userDao.findAllPharmacists();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method findAllPharmacists() ", e);
            throw new ServiceException("Exception is in method findAllPharmacists() " , e);
        }
        return pharmacists;
    }

    @Override
    public void changePharmacistStatus(String id, Status status) throws ServiceException {
        try {
            userDao.changePharmacistStatus(Long.parseLong(id), status);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method verifyPharmacist() " , e);
            throw new ServiceException("Exception is in method verifyPharmacist() " , e);
        }
    }

    @Override
    public List<User> findInactivePharmacists() throws ServiceException {
        List<User> inactivePharmacists;
        try {
            inactivePharmacists =userDao.findInactivePharmacists();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method findInactivePharmacists() ", e);
            throw new ServiceException("Exception is in method findInactivePharmacists() ", e);
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
            logger.log(Level.ERROR, "Exception is in method updateLogin() ", e);
            throw new ServiceException("Exception is in method updateLogin() ",  e);
        }
        return false;
    }

    @Override
    public void updateFirstName(long id, String firstName) throws ServiceException {
        try {
            userDao.updateFirstName(id, firstName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method updateFirstName() ", e);
            throw new ServiceException("Exception is in method updateFirstName() ", e);
        }
    }

}

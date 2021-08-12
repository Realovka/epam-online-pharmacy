package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.dto.UserAuthDto;
import by.epam.onlinepharmacy.dto.UserRegDto;
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
    public boolean createUser(UserRegDto userRegDto) throws ServiceException {
        try {
            if (userDao.findByLogin(userRegDto.getLogin()).isPresent()) {
                return false;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Such login already exists " + e.getMessage());
            throw new ServiceException("Such login already exists " + e.getMessage());
        }


        User user = new User.Builder()
                .setLogin(userRegDto.getLogin())
                .setPassword(PasswordEncoder.createPasswordEncoded(userRegDto.getPassword()))
                .setFirstName(userRegDto.getFirstName())
                .setLastName(userRegDto.getLastName())
                .setEmail(userRegDto.getEmail())
                .setTelephone(userRegDto.getTelephone())
                .setRole(userRegDto.getRole()).build();
        try {
            userDao.createUser(user);
        } catch (DaoException ex) {
            logger.log(Level.ERROR, "Exception is in method createUser() " + ex.getMessage());
            throw new ServiceException("Exception is in method createUser() " + ex.getMessage());
        }
        return true;
    }

    @Override
    public Optional<User> authenticationUser(UserAuthDto userAuthDto) throws ServiceException {
        Optional<User> userFromDb;
        String passwordEncoded = PasswordEncoder.createPasswordEncoded(userAuthDto.getPassword());
        User user = new User.Builder()
                .setLogin(userAuthDto.getLogin())
                .setPassword(passwordEncoded)
                .build();
        try {
           userFromDb = userDao.authenticationUser(user);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method authenticationUserByLoginAndPassword() " + e.getMessage());
            throw new ServiceException("Exception is in method authenticationUserByLoginAndPassword() " + e.getMessage());
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
            logger.log(Level.ERROR, "Exception is in method findAllPharmacists() " + e.getMessage());
            throw new ServiceException("Exception is in method findAllPharmacists() " + e.getMessage());
        }
        return pharmacists;
    }

    @Override
    public void verifyPharmacist(String id) throws ServiceException {
        try {
            userDao.verifyPharmacist(Integer.parseInt(id));
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method verifyPharmacists() " + e.getMessage());
            throw new ServiceException("Exception is in method verifyPharmacists() " + e.getMessage());
        }

    }

}

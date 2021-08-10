package by.epam.onlinepharmacy.service.impl;

import by.epam.onlinepharmacy.dao.UserDao;
import by.epam.onlinepharmacy.dao.impl.UserDaoImpl;
import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.ERROR, "No such algorithm  " + ex.getMessage());
            throw new ServiceException("No such algorithm  " + ex.getMessage());
        }
        byte[] loginHash = md5.digest(userRegDto.getLogin().getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte item : loginHash) {
            builder.append(String.format("%02X", item));
        }
        User user = new User.Builder()
                .setLogin(userRegDto.getLogin())
                .setPassword(builder.toString())
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
}

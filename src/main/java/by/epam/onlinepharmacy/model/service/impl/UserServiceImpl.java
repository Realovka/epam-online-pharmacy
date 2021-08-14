package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.dto.UserAuthDto;
import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.dto.UserViewDto;
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
import java.util.stream.Collectors;

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
            logger.log(Level.ERROR, "Exception is in method authenticationUser() " + e.getMessage());
            throw new ServiceException("Exception is in method authenticationUser() " + e.getMessage());
       }
       if(userFromDb.isEmpty()) {
           userFromDb = Optional.empty();
       }
       return userFromDb;
    }

    @Override
    public List<UserViewDto> findAllPharmacists() throws ServiceException {
        List<User> pharmacists;
        try {
           pharmacists =userDao.findAllPharmacists();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method findAllPharmacists() " + e.getMessage());
            throw new ServiceException("Exception is in method findAllPharmacists() " + e.getMessage());
        }
        return pharmacists.stream()
                .map(pharmacist-> new UserViewDto.Builder()
                        .setUserId(pharmacist.getUserId())
                        .setLogin(pharmacist.getLogin())
                        .setFirstName(pharmacist.getFirstName())
                        .setLastName(pharmacist.getLastName())
                        .setTelephone(pharmacist.getTelephone())
                        .setEmail(pharmacist.getEmail())
                        .setStatus(pharmacist.getStatus())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public void changePharmacistStatus(String id, Status status) throws ServiceException {
        try {
            userDao.changePharmacistStatus(Long.parseLong(id), status);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method verifyPharmacist() " + e.getMessage());
            throw new ServiceException("Exception is in method verifyPharmacist() " + e.getMessage());
        }
    }

    @Override
    public List<UserViewDto> findInactivePharmacists() throws ServiceException {
        List<User> inactivePharmacists;
        try {
            inactivePharmacists =userDao.findInactivePharmacists();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method findInactivePharmacists() " + e.getMessage());
            throw new ServiceException("Exception is in method findInactivePharmacists() " + e.getMessage());
        }
        return inactivePharmacists.stream()
                .map(pharmacist-> new UserViewDto.Builder()
                        .setUserId(pharmacist.getUserId())
                        .setFirstName(pharmacist.getFirstName())
                        .setLastName(pharmacist.getLastName())
                        .setStatus(pharmacist.getStatus())
                        .build()).collect(Collectors.toList());
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
            logger.log(Level.ERROR, "Exception is in method updateLogin() " + e.getMessage());
            throw new ServiceException("Exception is in method updateLogin() " + e.getMessage());
        }
        return false;
    }

    @Override
    public void updateFirstName(long id, String firstName) throws ServiceException {
        try {
            userDao.updateFirstName(id, firstName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method updateFirstName() " + e.getMessage());
            throw new ServiceException("Exception is in method updateFirstName() " + e.getMessage());
        }

    }


}

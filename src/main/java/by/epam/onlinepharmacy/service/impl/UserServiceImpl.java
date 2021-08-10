package by.epam.onlinepharmacy.service.impl;

import by.epam.onlinepharmacy.dao.UserDao;
import by.epam.onlinepharmacy.dao.impl.UserDaoImpl;
import by.epam.onlinepharmacy.dto.UserRegDto;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.service.UserService;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    public void createUser(UserRegDto userRegDto) {
//        if (userDao.containsByLogin(userRegDTO.getLoginUserDTO()).isPresent()) {
//            throw new DuplicateUserException("Such user is already");
//        }
        User user = new User(userRegDto.getLogin(),userRegDto.getPassword(), userRegDto.getFirstName(),
                userRegDto.getLastName(), userRegDto.getEmail(), userRegDto.getTelephone(), userRegDto.getRole());
        userDao.createUser(user);
    }

//    public boolean authorizeUser(UserAuthDTO userAuthDTO) {
//        User user = new User (userAuthDTO.getLoginAuthUser(),userAuthDTO.getPasswordAuthUser());
//        if (userDao.containsUser(user).isEmpty()) {
//            throw new NoSuchUserException("No such user in DB");
//        }
//        return true;
//    }
}

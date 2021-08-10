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
        User user = new User.Builder()
                .setLogin(userRegDto.getLogin())
                .setPassword(userRegDto.getPassword())
                .setFirstName(userRegDto.getFirstName())
                .setLastName(userRegDto.getLastName())
                .setEmail(userRegDto.getEmail())
                .setTelephone(userRegDto.getTelephone())
                .setRole(userRegDto.getRole()).build();
        userDao.createUser(user);
    }
}

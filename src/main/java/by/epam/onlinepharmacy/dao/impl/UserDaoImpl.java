package by.epam.onlinepharmacy.dao.impl;

import by.epam.onlinepharmacy.connection.ConnectionPool;

import by.epam.onlinepharmacy.dao.UserDao;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String CREATE_USER = """
            INSERT INTO users (login, password, first_name, last_name, email, telephone, role_id)
            VALUES(?, ?, ?, ?,  ?, ?, (SELECT role_id FROM user_role WHERE role=?))
             """;
    private static final String FIND_BY_LOGIN = "SELECT login FROM users WHERE login= ?";

    @Override
    public void createUser(User user) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_USER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getTelephone());
            ps.setString(7, user.getRole().name());
            ps.execute();
        } catch (SQLException e) {
           logger.log(Level.ERROR, "SQLException in method createUser() " + e.getMessage());
           throw new DaoException("SQLException in method createUser() " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User.Builder()
                            .setLogin(login)
                            .build());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createUser() " + e.getMessage());
            throw new DaoException("SQLException in method createUser() " + e.getMessage());
        }
        return Optional.empty();
    }
}

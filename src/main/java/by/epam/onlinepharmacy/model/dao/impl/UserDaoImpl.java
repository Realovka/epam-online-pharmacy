package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;

import by.epam.onlinepharmacy.model.dao.NameColumn;
import by.epam.onlinepharmacy.model.dao.UserDao;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    private static final String CREATE_USER = """
            INSERT INTO users (login, password, first_name, last_name, email, telephone, role_id)
            VALUES(?, ?, ?, ?, ?, ?, (SELECT role_id FROM user_role WHERE role=?))
             """;
    private static final String FIND_BY_LOGIN = "SELECT login FROM users WHERE login=?";

    private static final String IDENT_USER = """
            SELECT u.first_name, u.last_name, ur.role FROM users u JOIN user_role ur ON u.role_id=ur.role_id
            WHERE u.login=? AND u.password=?
            """;

    private static final String FIND_ALL_PHARMACISTS = """
            SELECT u.user_id, u.login, u.first_name, u.last_name, u.telephone, u.email, us.status FROM users u 
            JOIN user_status us ON u.status_id=us.status_id
            JOIN user_role ur ON u.role_id=ur.role_id WHERE ur.role='PHARMACIST'
             """;

    private static final String UPDATE_PHARMACIST_STATUS = """
            UPDATE users SET status_id = (SELECT status_id FROM user_status WHERE status=?) WHERE user_id=?
            """;

    private static final String FIND_INACTIVE_PHARMACISTS = """
            SELECT u.user_id, u.first_name, u.last_name, us.status FROM users u JOIN user_status us ON u.status_id=us.status_id
            JOIN user_role ur ON u.role_id=ur.role_id WHERE ur.role='PHARMACIST' AND us.status='INACTIVE'
             """;

    private static final String UPDATE_USER_LOGIN = "UPDATE users SET login =? WHERE user_id =?";
    private static final String UPDATE_USER_FIRST_NAME = "UPDATE users SET first_name =? WHERE user_id =?";
    private static final String UPDATE_USER_LAST_NAME = "UPDATE users SET last_name =? WHERE user_id =?";
    private static final String UPDATE_USER_EMAIL = "UPDATE users SET email =? WHERE user_id =?";
    private static final String UPDATE_USER_TELEPHONE = "UPDATE users SET telephone =? WHERE user_id =?";


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

    @Override
    public Optional<User> authenticationUser(User user) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IDENT_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User.Builder()
                            .setFirstName(rs.getString(NameColumn.FIRST_NAME))
                            .setLastName(rs.getString(NameColumn.LAST_NAME))
                            .setRole(Role.valueOf(rs.getString(NameColumn.ROLE)))
                            .build());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method authenticationUser() " + e.getMessage());
            throw new DaoException("SQLException in method authenticationUser() " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllPharmacists() throws DaoException {
        List<User> pharmacists = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PHARMACISTS)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    User user = new User.Builder()
                            .setUserId(rs.getLong(NameColumn.USER_ID))
                            .setLogin(rs.getString(NameColumn.LOGIN))
                            .setFirstName(rs.getString(NameColumn.FIRST_NAME))
                            .setLastName(rs.getString(NameColumn.LAST_NAME))
                            .setTelephone(rs.getString(NameColumn.TELEPHONE))
                            .setEmail(rs.getString(NameColumn.EMAIL))
                            .setStatus(Status.valueOf(rs.getString(NameColumn.STATUS)))
                            .build();
                    pharmacists.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllPharmacists() " + e.getMessage());
            throw new DaoException("SQLException in method findAllPharmacists() " + e.getMessage());
        }
        return pharmacists;
    }

    @Override
    public void changePharmacistStatus(long id, Status status) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHARMACIST_STATUS)) {
            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method changePharmacists() " + e.getMessage());
            throw new DaoException("SQLException in method changePharmacists() " + e.getMessage());
        }
    }

    @Override
    public List<User> findInactivePharmacists() throws DaoException {
        List<User> inactivePharmacists = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_INACTIVE_PHARMACISTS)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    User user = new User.Builder()
                            .setUserId(rs.getLong(NameColumn.USER_ID))
                            .setFirstName(rs.getString(NameColumn.FIRST_NAME))
                            .setLastName(rs.getString(NameColumn.LAST_NAME))
                            .setStatus(Status.valueOf(rs.getString(NameColumn.STATUS)))
                            .build();
                    inactivePharmacists.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findInactivePharmacists() " + e.getMessage());
            throw new DaoException("SQLException in method findInactivePharmacists() " + e.getMessage());
        }
        return inactivePharmacists;
    }

    @Override
    public void updateLogin(long id, String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateLogin() " + e.getMessage());
            throw new DaoException("SQLException in method updateLogin() " + e.getMessage());
        }
    }

    @Override
    public void updateFirstName(long id, String firstName) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_FIRST_NAME)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateFirstName() " + e.getMessage());
            throw new DaoException("SQLException in method updateFirstName() " + e.getMessage());
        }
    }

    @Override
    public void updateLastName(long id, String lastName) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LAST_NAME)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateLastName() " + e.getMessage());
            throw new DaoException("SQLException in method updateLastName() " + e.getMessage());
        }
    }

    @Override
    public void updateEmail(long id, String email) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateEmail() " + e.getMessage());
            throw new DaoException("SQLException in method updateEmail() " + e.getMessage());
        }
    }

    @Override
    public void updateTelephone(long id, String telephone) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_TELEPHONE)) {
            preparedStatement.setString(1, telephone);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateTelephone() " + e.getMessage());
            throw new DaoException("SQLException in method updateTelephone() " + e.getMessage());
        }
    }


}

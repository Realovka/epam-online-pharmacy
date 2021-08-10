package by.epam.onlinepharmacy.dao.impl;

import by.epam.onlinepharmacy.connection.ConnectionPool;

import by.epam.onlinepharmacy.dao.UserDao;
import by.epam.onlinepharmacy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String CREATE_USER = """
        INSERT INTO users (login, password, first_name, last_name, email, telephone, role_id)
        VALUES(?, ?, ?, ?,  ?, ?, (SELECT role_id FROM user_role WHERE role=?))
         """;

    @Override
    public void createUser(User user)  {
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
            e.printStackTrace();
        }
    }
}

package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;
import by.epam.onlinepharmacy.model.dao.OrderDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private OrderDaoImpl() {
    }
    private static OrderDaoImpl instance = new OrderDaoImpl();

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    private static final String CREATE_ORDER = "INSERT INTO orders (data_starting, data_ending, pharmacy_id) VALUES (?, ?, ?)";

    @Override
    public void createOrder(Order order) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER)) {
            preparedStatement.setTimestamp(1, order.getDataStarting());
            preparedStatement.setTimestamp(2, order.getDataEnding());
            preparedStatement.setLong(3, order.getPharmacyId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createOrder() ", e);
            throw new DaoException("SQLException in method createOrder() ", e);
        }
    }
}

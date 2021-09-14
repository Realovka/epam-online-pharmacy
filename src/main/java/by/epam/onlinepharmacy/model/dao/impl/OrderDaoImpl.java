package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;
import by.epam.onlinepharmacy.model.dao.ColumnName;
import by.epam.onlinepharmacy.model.dao.OrderDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    private static final String FIND_LAST_INSERT_ORDER_ID = """
            SELECT order_id FROM orders WHERE order_id = last_insert_id()
            """;

    private static final String CREATE_PRODUCTS_IN_BASKET = """
            INSERT INTO basket (user_id, product_id, order_id, quantity) VALUES (?, ?, ?, ?)
            """;

    @Override
    public long createOrder(Order order) throws DaoException {
        long orderId = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER)) {
            preparedStatement.setTimestamp(1, order.getDataStarting());
            preparedStatement.setTimestamp(2, order.getDataEnding());
            preparedStatement.setLong(3, order.getPharmacyId());
            preparedStatement.execute();
            try (PreparedStatement statement = connection.prepareStatement(FIND_LAST_INSERT_ORDER_ID);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderId = resultSet.getLong(ColumnName.ORDER_ID);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createOrder() ", e);
            throw new DaoException("SQLException in method createOrder() ", e);
        }
        return orderId;
    }

    @Override
    public void createProductsInBasket(List<Basket> basket) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCTS_IN_BASKET)) {
            for (Basket item : basket) {
                preparedStatement.setLong(1, basket.get(0).getUserId());
                preparedStatement.setLong(2, item.getProductId());
                preparedStatement.setLong(3, basket.get(0).getOrderId());
                preparedStatement.setInt(4, item.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProductInBasket() ", e);
            throw new DaoException("SQLException in method createProductInBasket() ", e);
        }
    }
}

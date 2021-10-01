package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.onlinepharmacy.model.dao.ColumnName.*;

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

    private static final String FIND_PROCESSING_ORDERS = """
            SELECT o.order_id, o.data_starting, o.data_ending, p.number, os.status FROM
            orders o JOIN pharmacies p ON o.pharmacy_id=p.pharmacy_id
            JOIN order_status os ON o.order_status_id = os.order_status_id WHERE o.pharmacy_id = ? AND os.order_status_id = 1
            """;

    private static final String FIND_PRODUCTS_IN_ORDER = """
            SELECT u.first_name, u.last_name, u.telephone,
            p.product_name, p.product_dose, p.plant, p.price,
            b.quantity FROM basket b JOIN users u ON b.user_id = u.user_id
            JOIN products p ON b.product_id = p.product_id
            WHERE order_id = ?
            """;

    private static final String FIND_ORDER_BY_ID = """
            SELECT o.order_id, o.data_starting, o.data_ending, o.pharmacy_id, os.status FROM orders o JOIN order_status os
            ON o.order_status_id = os.order_status_id WHERE order_id = ?
            """;

    private static final String UPDATE_ORDER_STATUS = """
            UPDATE orders set order_status_id = ? WHERE order_id = ?
            """;

    @Override
    public Order createOrder(Order order) throws DaoException {
        Order orderDb = new Order();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER)) {
            preparedStatement.setTimestamp(1, order.getDataStarting());
            preparedStatement.setTimestamp(2, order.getDataEnding());
            preparedStatement.setLong(3, order.getPharmacy().getPharmacyId());
            preparedStatement.execute();
            try (PreparedStatement statement = connection.prepareStatement(FIND_LAST_INSERT_ORDER_ID);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderDb = new Order.Builder()
                            .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createOrder() ", e);
            throw new DaoException("SQLException in method createOrder() ", e);
        }
        return orderDb;
    }

    @Override
    public void createProductsInBasket(List<Basket> basket) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCTS_IN_BASKET)) {
            for (Basket item : basket) {
                preparedStatement.setLong(1, basket.get(0).getUser().getUserId());
                preparedStatement.setLong(2, item.getProduct().getProductId());
                preparedStatement.setLong(3, basket.get(0).getOrder().getOrderId());
                preparedStatement.setInt(4, item.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProductInBasket() ", e);
            throw new DaoException("SQLException in method createProductInBasket() ", e);
        }
    }

    @Override
    public List<Order> findAllProcessingOrdersForPharmacies(long pharmacyId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PROCESSING_ORDERS)) {
            preparedStatement.setLong(1, pharmacyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order.Builder()
                            .setOrderId(resultSet.getLong(ORDER_ID))
                            .setDataStarting(resultSet.getTimestamp(DATA_STARTING))
                            .setDataEnding(resultSet.getTimestamp(DATA_ENDING))
                            .setStatusOrder(StatusOrder.valueOf(resultSet.getString(ORDER_STATUS)))
                            .setPharmacy(new Pharmacy.Builder()
                                    .setNumber(resultSet.getInt(PHARMACY_NUMBER))
                                    .build())
                            .build();
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllProcessingOrdersForPharmacies() ", e);
            throw new DaoException("SQLException in method findAllProcessingOrdersForPharmacies() ", e);
        }
        return orders;
    }

    @Override
    public List<Basket> findBasketForOrder(long orderId) throws DaoException {
        List<Basket> basket = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCTS_IN_ORDER)) {
            preparedStatement.setLong(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Basket basketDB = new Basket.Builder()
                            .setProduct(new Product.Builder()
                                    .setName(resultSet.getString(PRODUCT_NAME))
                                    .setDose(resultSet.getString(PRODUCT_DOSE))
                                    .setPlant(resultSet.getString(PRODUCT_PLANT))
                                    .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                                    .build())
                            .setUser(new User.Builder()
                                    .setFirstName(resultSet.getString(USER_FIRST_NAME))
                                    .setLastName(resultSet.getString(USER_LAST_NAME))
                                    .setTelephone(resultSet.getString(USER_TELEPHONE))
                                    .build())
                            .setQuantity(resultSet.getInt(QUANTITY))
                            .build();
                    basket.add(basketDB);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findProductsInOrder() ", e);
            throw new DaoException("SQLException in method findProductsInOrder() ", e);
        }
        return basket;
    }

    @Override
    public Optional<Order> findOrderById(long orderId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID)) {
            preparedStatement.setLong(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order.Builder()
                            .setOrderId(resultSet.getLong(ORDER_ID))
                            .setDataStarting(resultSet.getTimestamp(DATA_STARTING))
                            .setDataEnding(resultSet.getTimestamp(DATA_ENDING))
                            .setPharmacy(new Pharmacy.Builder()
                                    .setPharmacyId(resultSet.getLong(PHARMACY_ID))
                                    .build())
                            .setStatusOrder(StatusOrder.valueOf(resultSet.getString(ORDER_STATUS)))
                            .build();
                    return Optional.of(order);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findOrderById() ", e);
            throw new DaoException("SQLException in method findOrderById() ", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateStatusOrder(int statusId, long orderId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            preparedStatement.setInt(1, statusId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateStatusOrder() ", e);
            throw new DaoException("SQLException in method updateStatusOrder() ", e);
        }
    }
}
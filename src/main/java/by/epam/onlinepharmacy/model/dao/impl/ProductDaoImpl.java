package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;
import by.epam.onlinepharmacy.model.dao.ProductDao;
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

public class ProductDaoImpl implements ProductDao {

    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static ProductDaoImpl instance = new ProductDaoImpl();

    private ProductDaoImpl() {
    }

    public static ProductDaoImpl getInstance() {
        return instance;
    }

    private static final String CREATE_PRODUCT = """
            INSERT INTO products (product_name, product_group, price, recipe, instruction) VALUES (?,?,?,?,?)
            """;

    private static final String ADD_PICTURE = "UPDATE products SET picture=? WHERE product_id=?";

    private static final String FIND_ALL_PRODUCTS = """
            SELECT product_id, product_name, product_group, price, recipe, instruction FROM products
            """;
    private static final String FIND_PRODUCT_FOR_ORDER = """
            SELECT product_id, product_name FROM products WHERE product_id = ?
            """;

    private static final String FIND_PICTURE = "SELECT picture FROM products WHERE product_id=?";

    private static final String FIND_PRODUCT_BY_ID = """
            SELECT product_name, product_group, price, recipe, instruction, picture FROM products WHERE product_id = ?
            """;


    @Override
    public void createProduct(Product product) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getGroup());
            ps.setBigDecimal(3, product.getPrice());
            ps.setBoolean(4, product.isRecipe());
            ps.setString(5, product.getInstruction());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProduct() ", e);
            throw new DaoException("SQLException in method createProduct() ", e);
        }
    }

    @Override
    public void addPathToPicture(long id, String fileNAme) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(ADD_PICTURE)) {
            ps.setString(1, fileNAme);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method addPathToPicture() ", e);
            throw new DaoException("SQLException in method addPathToPicture() ", e);
        }
    }

    @Override
    public List<Product> findAllProducts() throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCTS)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product.Builder()
                            .setProductId(rs.getLong(PRODUCT_ID))
                            .setName(rs.getString(PRODUCT_NAME))
                            .setGroup(rs.getString(PRODUCT_GROUP))
                            .setPrice(rs.getBigDecimal(PRODUCT_PRICE))
                            .setInstruction(rs.getString(PRODUCT_INSTRUCTION))
                            .build();
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllProducts() ", e);
            throw new DaoException("SQLException in method findAllProducts() ", e);
        }
        return products;
    }



    @Override
    public Optional<String> findPathToPicture(long id) throws DaoException {
        String pathToFile = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PICTURE)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pathToFile = resultSet.getString(PRODUCT_PICTURE);
                    return Optional.of(pathToFile);

                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findPathToPicture() ", e);
            throw new DaoException("SQLException in method findPathToPicture() ", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findProductById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product.Builder()
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                            .setGroup(resultSet.getString(PRODUCT_GROUP))
                            .isRecipe(resultSet.getBoolean(PRODUCT_RECIPE))
                            .setInstruction(resultSet.getString(PRODUCT_INSTRUCTION))
                            .setPicture(resultSet.getString(PRODUCT_PICTURE))
                            .build();
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findProductById() ", e);
            throw new DaoException("SQLException in method findProductById() ", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findProductForOrderById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_FOR_ORDER)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product.Builder()
                            .setProductId(resultSet.getLong(PRODUCT_ID))
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .build();
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findProductForOrderById ", e);
            throw new DaoException("SQLException in method findProductForOrderById ", e);
        }
        return Optional.empty();
    }
}

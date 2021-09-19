package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;
import by.epam.onlinepharmacy.model.dao.ProductDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
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

    private static final String COUNT_PRODUCTS = "SELECT COUNT(*) FROM products";

    private static final String ADD_PICTURE = "UPDATE products SET picture=? WHERE product_id=?";

    private static final String FIND_PRODUCTS = """
            SELECT product_id, product_name, product_group, price, recipe, instruction FROM products LIMIT ?, 5
            """;
    private static final String FIND_PRODUCT_FOR_ORDER = """
            SELECT product_id, product_name FROM products WHERE product_id = ?
            """;

    private static final String FIND_PICTURE = "SELECT picture FROM products WHERE product_id=?";

    private static final String FIND_PRODUCT_BY_ID = """
            SELECT product_name, product_group, price, recipe, instruction, picture FROM products WHERE product_id = ?
            """;

    private static final String UPDATE_PRODUCT_NAME = "UPDATE products SET product_name=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_GROUP = "UPDATE products SET product_group=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_PRICE = "UPDATE products SET price=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_RECIPE = "UPDATE products SET recipe=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_INSTRUCTION = "UPDATE products SET instruction=? WHERE product_id=?";


    @Override
    public void createProduct(Product product) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getGroup());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setBoolean(4, product.isRecipe());
            preparedStatement.setString(5, product.getInstruction());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProduct() ", e);
            throw new DaoException("SQLException in method createProduct() ", e);
        }
    }

    @Override
    public void addPathToPicture(long id, String fileNAme) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PICTURE)) {
            preparedStatement.setString(1, fileNAme);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method addPathToPicture() ", e);
            throw new DaoException("SQLException in method addPathToPicture() ", e);
        }
    }

    @Override
    public List<Product> findProducts(int startingProduct) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCTS)) {
            preparedStatement.setInt(1, startingProduct);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product.Builder()
                            .setProductId(resultSet.getLong(PRODUCT_ID))
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .setGroup(resultSet.getString(PRODUCT_GROUP))
                            .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                            .isRecipe(resultSet.getBoolean(PRODUCT_RECIPE))
                            .setInstruction(resultSet.getString(PRODUCT_INSTRUCTION))
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
    public int findProductsNumber() throws DaoException {
        int productsNumber = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PRODUCTS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productsNumber = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findProductsNumber() ", e);
            throw new DaoException("SQLException in method findProductsNumber() ", e);
        }
        return productsNumber;
    }



    @Override
    public Optional<String> findPathToPicture(long id) throws DaoException {
        String pathToFile;
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

    @Override
    public void updateProductName(long id, String name) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductName() ", e);
            throw new DaoException("SQLException in method updateProductName() ", e);
        }
    }

    @Override
    public void updateProductGroup(long id, String group) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_GROUP)) {
            preparedStatement.setString(1, group);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductGroup() ", e);
            throw new DaoException("SQLException in method updateProductGroup() ", e);
        }
    }

    @Override
    public void updateProductPrice(long id, BigDecimal price) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_PRICE)) {
            preparedStatement.setBigDecimal(1, price);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductPrice() ", e);
            throw new DaoException("SQLException in method updateProductPrice() ", e);
        }
    }

    @Override
    public void updateProductRecipe(long id, boolean recipe) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_RECIPE)) {
            preparedStatement.setBoolean(1, recipe);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductRecipe() ", e);
            throw new DaoException("SQLException in method updateProductRecipe() ", e);
        }
    }

    @Override
    public void updateProductInstruction(long id, String instruction) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_INSTRUCTION)) {
            preparedStatement.setString(1, instruction);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductInstruction() ", e);
            throw new DaoException("SQLException in method updateProductInstruction() ", e);
        }
    }

}

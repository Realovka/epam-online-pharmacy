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

import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_DOSE;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_GROUP;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_ID;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_INSTRUCTION;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_NAME;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_NON_PROPRIETARY_NAME;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_PICTURE;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_PLANT;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_PRICE;
import static by.epam.onlinepharmacy.model.dao.ColumnName.PRODUCT_RECIPE;

/**
 * The type Product dao.
 */
public class ProductDaoImpl implements ProductDao {

    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static ProductDaoImpl instance = new ProductDaoImpl();

    private ProductDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ProductDaoImpl getInstance() {
        return instance;
    }

    private static final String CREATE_PRODUCT = """
            INSERT INTO products (product_name, inpn, product_dose, product_group, plant, price, recipe, instruction) 
            VALUES (?,?,?,?,?,?,?,?)
            """;

    private static final String COUNT_PRODUCTS = "SELECT COUNT(*) FROM products";

    private static final String ADD_PICTURE = "UPDATE products SET picture=? WHERE product_id=?";

    private static final String FIND_PRODUCTS = """
            SELECT product_id, product_name, inpn, product_dose, plant, product_group, price, 
            recipe, instruction FROM products LIMIT ?, 5
            """;

    private static final String FIND_PRODUCTS_BY_NAME = """
            SELECT product_id, product_name, inpn, product_dose, plant, product_group, price, 
            recipe FROM products WHERE product_name = ?
            """;

    private static final String FIND_PRODUCTS_BY_NON_PROPRIETARY_NAME = """
            SELECT product_id, product_name, inpn, product_dose, plant, product_group, price, 
            recipe FROM products WHERE inpn = ?
            """;

    private static final String FIND_PRODUCT_FOR_ORDER = """
            SELECT product_id, product_name, product_dose, plant, price FROM products WHERE product_id = ?
            """;

    private static final String FIND_PICTURE = "SELECT picture FROM products WHERE product_id=?";

    private static final String FIND_PRODUCT_BY_ID = """
            SELECT product_name, inpn, product_dose, plant, product_group, price, recipe, instruction, picture
            FROM products WHERE product_id = ?
            """;

    private static final String UPDATE_PRODUCT_NAME = "UPDATE products SET product_name=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_NON_PROPRIETARY_NAME = """
            UPDATE products SET inpn=? WHERE product_id=?""";
    private static final String UPDATE_PRODUCT_DOSE = "UPDATE products SET product_dose=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_PLANT = "UPDATE products SET plant=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_GROUP = "UPDATE products SET product_group=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_PRICE = "UPDATE products SET price=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_RECIPE = "UPDATE products SET recipe=? WHERE product_id=?";
    private static final String UPDATE_PRODUCT_INSTRUCTION = "UPDATE products SET instruction=? WHERE product_id=?";


    @Override
    public int createProduct(Product product) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getNonProprietaryName());
            preparedStatement.setString(3, product.getDose());
            preparedStatement.setString(4, product.getPlant());
            preparedStatement.setString(5, product.getGroup());
            preparedStatement.setBigDecimal(6, product.getPrice());
            preparedStatement.setBoolean(7, product.isRecipe());
            preparedStatement.setString(8, product.getInstruction());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProduct() ", e);
            throw new DaoException("SQLException in method createProduct() ", e);
        }
        return result;
    }

    @Override
    public int addPathToPicture(long id, String fileName) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PICTURE)) {
            preparedStatement.setString(1, fileName);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method addPathToPicture() ", e);
            throw new DaoException("SQLException in method addPathToPicture() ", e);
        }
        return result;
    }

    @Override
    public List<Product> findListProducts(int startingProduct) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCTS)) {
            preparedStatement.setInt(1, startingProduct);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product.Builder()
                            .setProductId(resultSet.getLong(PRODUCT_ID))
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .setNonProprietaryName(resultSet.getString(PRODUCT_NON_PROPRIETARY_NAME))
                            .setDose(resultSet.getString(PRODUCT_DOSE))
                            .setPlant(resultSet.getString(PRODUCT_PLANT))
                            .setGroup(resultSet.getString(PRODUCT_GROUP))
                            .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                            .isRecipe(resultSet.getBoolean(PRODUCT_RECIPE))
                            .setInstruction(resultSet.getString(PRODUCT_INSTRUCTION))
                            .build();
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findListProducts() ", e);
            throw new DaoException("SQLException in method findListProducts() ", e);
        }
        return products;
    }

    @Override
    public List<Product> findListProductsByName(String productName) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCTS_BY_NAME)) {
            preparedStatement.setString(1, productName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product.Builder()
                            .setProductId(resultSet.getLong(PRODUCT_ID))
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .setNonProprietaryName(resultSet.getString(PRODUCT_NON_PROPRIETARY_NAME))
                            .setDose(resultSet.getString(PRODUCT_DOSE))
                            .setPlant(resultSet.getString(PRODUCT_PLANT))
                            .setGroup(resultSet.getString(PRODUCT_GROUP))
                            .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                            .isRecipe(resultSet.getBoolean(PRODUCT_RECIPE))
                            .build();
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findListProductsByName() ", e);
            throw new DaoException("SQLException in method findListProductsByName() ", e);
        }
        return products;
    }

    @Override
    public List<Product> findListProductsByNonProprietaryName(String nonProprietaryName) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCTS_BY_NON_PROPRIETARY_NAME)) {
            preparedStatement.setString(1, nonProprietaryName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product.Builder()
                            .setProductId(resultSet.getLong(PRODUCT_ID))
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .setNonProprietaryName(resultSet.getString(PRODUCT_NON_PROPRIETARY_NAME))
                            .setDose(resultSet.getString(PRODUCT_DOSE))
                            .setPlant(resultSet.getString(PRODUCT_PLANT))
                            .setGroup(resultSet.getString(PRODUCT_GROUP))
                            .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                            .isRecipe(resultSet.getBoolean(PRODUCT_RECIPE))
                            .build();
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findListProductsByNonProprietaryName() ", e);
            throw new DaoException("SQLException in method findListProductsByNonProprietaryName() ", e);
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
        String pathToFile = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PICTURE)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pathToFile = resultSet.getString(PRODUCT_PICTURE);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findPathToPicture() ", e);
            throw new DaoException("SQLException in method findPathToPicture() ", e);
        }
        return Optional.ofNullable(pathToFile);
    }

    @Override
    public Optional<Product> findProductById(long id) throws DaoException {
        Product product = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product.Builder()
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .setNonProprietaryName(resultSet.getString(PRODUCT_NON_PROPRIETARY_NAME))
                            .setDose(resultSet.getString(PRODUCT_DOSE))
                            .setPlant(resultSet.getString(PRODUCT_PLANT))
                            .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                            .setGroup(resultSet.getString(PRODUCT_GROUP))
                            .isRecipe(resultSet.getBoolean(PRODUCT_RECIPE))
                            .setInstruction(resultSet.getString(PRODUCT_INSTRUCTION))
                            .setPicture(resultSet.getString(PRODUCT_PICTURE))
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findProductById() ", e);
            throw new DaoException("SQLException in method findProductById() ", e);
        }
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> findProductForOrderById(long id) throws DaoException {
        Product product = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_FOR_ORDER)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product.Builder()
                            .setProductId(resultSet.getLong(PRODUCT_ID))
                            .setName(resultSet.getString(PRODUCT_NAME))
                            .setDose(resultSet.getString(PRODUCT_DOSE))
                            .setPlant(resultSet.getString(PRODUCT_PLANT))
                            .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findProductForOrderById() ", e);
            throw new DaoException("SQLException in method findProductForOrderById() ", e);
        }
        return Optional.ofNullable(product);
    }

    @Override
    public int updateProductName(long id, String name) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductName() ", e);
            throw new DaoException("SQLException in method updateProductName() ", e);
        }
        return result;
    }


    @Override
    public int updateProductNonProprietaryName(long id, String nonProprietaryName) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_NON_PROPRIETARY_NAME)) {
            preparedStatement.setString(1, nonProprietaryName);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductNonProprietaryName() ", e);
            throw new DaoException("SQLException in method updateProductNonProprietaryName() ", e);
        }
        return result;
    }

    @Override
    public int updateProductDose(long id, String dose) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_DOSE)) {
            preparedStatement.setString(1, dose);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductDose() ", e);
            throw new DaoException("SQLException in method updateProductDose() ", e);
        }
        return result;
    }

    @Override
    public int updateProductPlant(long id, String plant) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_PLANT)) {
            preparedStatement.setString(1, plant);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductPlant() ", e);
            throw new DaoException("SQLException in method updateProductPlant() ", e);
        }
        return result;
    }

    @Override
    public int updateProductGroup(long id, String group) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_GROUP)) {
            preparedStatement.setString(1, group);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductGroup() ", e);
            throw new DaoException("SQLException in method updateProductGroup() ", e);
        }
        return result;
    }

    @Override
    public int updateProductPrice(long id, BigDecimal price) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_PRICE)) {
            preparedStatement.setBigDecimal(1, price);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductPrice() ", e);
            throw new DaoException("SQLException in method updateProductPrice() ", e);
        }
        return result;
    }

    @Override
    public int updateProductRecipe(long id, boolean recipe) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_RECIPE)) {
            preparedStatement.setBoolean(1, recipe);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductRecipe() ", e);
            throw new DaoException("SQLException in method updateProductRecipe() ", e);
        }
        return result;
    }

    @Override
    public int updateProductInstruction(long id, String instruction) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_INSTRUCTION)) {
            preparedStatement.setString(1, instruction);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateProductInstruction() ", e);
            throw new DaoException("SQLException in method updateProductInstruction() ", e);
        }
        return result;
    }

}

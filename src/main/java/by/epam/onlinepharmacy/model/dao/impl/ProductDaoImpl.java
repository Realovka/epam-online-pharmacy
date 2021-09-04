package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;
import by.epam.onlinepharmacy.model.dao.ProductDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    private static final String ADD_PICTURE = "UPDATE products SET picture=? WHERE product_id=1";

    @Override
    public void createProduct(Product product) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getGroup());
            ps.setBigDecimal(3, product.getPrice());
            ps.setBoolean(4, product.isRecipe());
//            ps.setBinaryStream(4, new FileInputStream(product.getPicture()));
            ps.setString(5, product.getInstruction());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProduct() ", e);
            throw new DaoException("SQLException in method createProduct() ", e);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        }
    }

    @Override
    public void addPicture(File file) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(ADD_PICTURE)) {
            ps.setBinaryStream(1, new FileInputStream(file));
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProduct() ", e);
            throw new DaoException("SQLException in method createProduct() ", e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

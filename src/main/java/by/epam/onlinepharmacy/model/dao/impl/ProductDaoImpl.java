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
import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao {

    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static ProductDaoImpl instance = new ProductDaoImpl();
    private ProductDaoImpl(){
    }
    public static ProductDaoImpl getInstance() {
        return instance;
    }

    private static final String CREATE_PRODUCT = """
            INSERT INTO products (name, group, price, recipe, picture, instruction) VALUES (?,?,?,?,?,?)
            """;

    @Override
    public void createProduct(Product product) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getGroup());
            ps.setBigDecimal(2, product.getPrice());
            ps.setBoolean(3, product.isRecipe());
            ps.setBlob(4, product.getPicture());
            ps.setString(5, product.getInstruction());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createProduct() ", e);
            throw new DaoException("SQLException in method createProduct() ", e);
        }
    }
}

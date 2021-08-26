package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.dao.ProductDao;
import by.epam.onlinepharmacy.model.dao.impl.ProductDaoImpl;
import by.epam.onlinepharmacy.model.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductServiceImpl implements ProductService {
    private Logger logger = LogManager.getLogger();
    private static ProductServiceImpl instance = new ProductServiceImpl();
    private ProductDao productDao = ProductDaoImpl.getInstance();

    private ProductServiceImpl() {
    }

    public static ProductServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void createProduct(Product product) throws DaoException {
        productDao.createProduct(product);
    }
}

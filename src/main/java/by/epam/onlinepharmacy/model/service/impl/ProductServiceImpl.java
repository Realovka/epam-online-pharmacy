package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.ProductDao;
import by.epam.onlinepharmacy.model.dao.impl.ProductDaoImpl;
import by.epam.onlinepharmacy.model.service.ProductService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

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
    public void createProduct(String name, String group, String price, String recipe, String instruction) throws ServiceException {
        Product product = new Product.Builder()
                .setName(name)
                .setGroup(group)
                .setPrice(BigDecimal.valueOf(Double.parseDouble(price)))
                .isRecipe(Boolean.parseBoolean(recipe))
                .setInstruction(instruction)
                .build();
        try {
            productDao.createProduct(product);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method createProduct() ", e);
            throw new ServiceException("Exception is in method createProduct() ", e);
        }
    }

    @Override
    public void addPicture(long id, String fileName){
        try {
            productDao.addPicture(id, fileName);
        } catch (DaoException e) {
            e.printStackTrace(); //TODO
        }
    }

    public String find(long id){
        String s = null;
        try {
          s=  productDao.findPic(id);
        } catch (DaoException e) {
            e.printStackTrace(); //TODO
        }
        return s;
    }

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        List<Product> products;
        try {
            products = productDao.findAllProducts();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method findAllProducts() ", e);
            throw new ServiceException("Exception is in method findAllProducts() ", e);
        }
        return products;
    }
}

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

import java.math.BigDecimal;
import java.util.*;

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
            logger.log(Level.ERROR, "DaoException is in method createProduct() ", e);
            throw new ServiceException("DaoException is in method createProduct() ", e);
        }
    }

    @Override
    public void addPathToPicture(long id, String fileName) throws ServiceException {
        try {
            productDao.addPathToPicture(id, fileName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method addPathToPicture() ", e);
            throw new ServiceException("DaoException is in method addPathToProduct() ", e);
        }
    }

    @Override
    public Optional<String> findPathToPicture(long id) throws ServiceException {
        Optional<String> pathToPicture;
        try {
          pathToPicture = productDao.findPathToPicture(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPathToPicture() ", e);
            throw new ServiceException("DaoException is in method findPathToProduct() ", e);
        }
        return pathToPicture;
    }

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        List<Product> products;
        try {
            products = productDao.findAllProducts();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllProducts() ", e);
            throw new ServiceException("DaoException is in method findAllProducts() ", e);
        }
        return products;
    }

    @Override
    public Optional<Product> findProductById(String id) throws ServiceException {
        Optional<Product> product;
        try {
            long productId = Long.parseLong(id);
            product = productDao.findProductById(productId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findProductById() ", e);
            throw new ServiceException("DaoException is in method findProductById() ", e);
        }
        return product;
    }

    @Override
    public Map<Product, Integer> addProductToOrder(String id, Map<Product, Integer> order) throws ServiceException {
        Optional<Product> product;
        try {
            long productId = Long.parseLong(id);
            product = productDao.findProductForOrderById(productId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method addProductToOrder() ", e);
            throw new ServiceException("DaoException is in method addProductToOrder() ", e);
        }
        product.ifPresent(productToOrder -> {
            order.put(productToOrder, 1);
        });
        return order;
    }

    @Override
    public void updateProductQuantityInOrder(String productId, String quantity, Map<Product, Integer> order) {
        long id = Long.parseLong(productId);
        Integer newQuantity = Integer.valueOf(quantity);
        Optional<Product> updatingProduct = order.keySet().stream()
                .filter(product -> product.getProductId() == id)
                .findFirst();
        order.put(updatingProduct.get(), newQuantity);
    }
}

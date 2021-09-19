package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.ProductDao;
import by.epam.onlinepharmacy.model.dao.impl.ProductDaoImpl;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.validation.ProductValidator;
import by.epam.onlinepharmacy.model.validation.impl.ProductValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private Logger logger = LogManager.getLogger();
    private static final String NEED_RECIPE = "Yes";
    private static final String DONT_NEED_RECIPE = "No";
    private static final int RECORD_PER_PAGE = 15;
    private static ProductServiceImpl instance = new ProductServiceImpl();
    private ProductDao productDao = ProductDaoImpl.getInstance();

    private ProductServiceImpl() {
    }

    public static ProductServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Product> createProduct(String name, String group, String price, String recipe, String instruction) throws ServiceException {
        List<Product> products;
        boolean needRecipe = needProductRecipe(recipe);
        Product product = new Product.Builder()
                .setName(name)
                .setGroup(group)
                .setPrice(BigDecimal.valueOf(Double.parseDouble(price)))
                .isRecipe(needRecipe)
                .setInstruction(instruction)
                .build();
        try {
            productDao.createProduct(product);
                int productsNumber = productDao.findProductsNumber();
                int productsOnLastPage = productsNumber % RECORD_PER_PAGE;
                int pages = productsNumber / RECORD_PER_PAGE;
                if (productsOnLastPage == 0) {
                    products = productDao.findProducts(pages * RECORD_PER_PAGE - RECORD_PER_PAGE);
                } else {
                    products = productDao.findProducts(pages * RECORD_PER_PAGE);
                }

        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createProduct() ", e);
            throw new ServiceException("DaoException is in method createProduct() ", e);
        }
        return products;
    }

    @Override
    public Map<String, String> isValidParameters(String name, String group, String price, String instruction) {
        Map<String, String> productParameters = new HashMap<>();
        productParameters.put(RequestParameter.NAME, name);
        productParameters.put(RequestParameter.GROUP, group);
        productParameters.put(RequestParameter.PRICE, price);
        productParameters.put(RequestParameter.INSTRUCTION, instruction);
        ProductValidator productValidator = ProductValidatorImpl.getInstance();
        productValidator.isValidForm(productParameters);
        return productParameters;
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
    public List<ProductDto> findListProducts(int startingProduct) throws ServiceException {
        List<ProductDto> products;
        try {
            List<Product> productsDb = productDao.findProducts(startingProduct);
            products = productsDb.stream()
                    .map(product -> new ProductDto.Builder()
                            .setProductId(product.getProductId())
                            .setName(product.getName())
                            .setGroup(product.getGroup())
                            .setPrice(product.getPrice())
                            .setRecipe(convertProductRecipe(product.isRecipe()))
                            .setInstruction(product.getInstruction())
                            .build()).collect(Collectors.toList());

        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListProducts() ", e);
            throw new ServiceException("DaoException is in method findListProducts() ", e);
        }
        return products;
    }

    @Override
    public int findCurrentPage() throws ServiceException {
        int productsNumber;
        int currentPage;
        try {
            productsNumber = productDao.findProductsNumber();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findCurrentPage() ", e);
            throw new ServiceException("DaoException is in method findCurrentPage() ", e);
        }
        if (productsNumber % RECORD_PER_PAGE == 0) {
            currentPage = productsNumber / RECORD_PER_PAGE;
        } else {
            currentPage = productsNumber / RECORD_PER_PAGE + 1;
        }
        return currentPage;
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
    public ProductDto findInstructionByProductId(long productId) throws ServiceException {
        ProductDto productDto;
        try {
            Product product = productDao.findProductById(productId).orElse(new Product());
            productDto = new ProductDto.Builder()
                    .setInstruction(product.getInstruction())
                    .build();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findInstructionByProductId() ", e);
            throw new ServiceException("DaoException is in method findInstructionByProductId() ", e);
        }
        return productDto;
    }

    @Override
    public void updateProductQuantityInOrder(String productId, String quantity, Map<Product, Integer> order) {
        long id = Long.parseLong(productId);
        Integer newQuantity = Integer.valueOf(quantity);
        Optional<Product> updatingProduct = order.keySet().stream()
                .filter(product -> product.getProductId() == id)
                .findFirst();
        updatingProduct.ifPresent(product -> {
            order.put(updatingProduct.get(), newQuantity);
        });
    }

    @Override
    public void updateProductName(long productId, String name) throws ServiceException {
        try {
            productDao.updateProductName(productId, name);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductName() ", e);
            throw new ServiceException("DaoException is in method updateProductName() ", e);
        }
    }

    @Override
    public void updateProductGroup(long productId, String group) throws ServiceException {
        try {
            productDao.updateProductGroup(productId, group);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductGroup() ", e);
            throw new ServiceException("DaoException is in method updateProductGroup() ", e);
        }
    }

    @Override
    public void updateProductPrice(long productId, String price) throws ServiceException {
        BigDecimal newPrice = new BigDecimal(price);
        try {
            productDao.updateProductPrice(productId, newPrice);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductPrice() ", e);
            throw new ServiceException("DaoException is in method updateProductPrice() ", e);
        }
    }

    @Override
    public void updateProductRecipe(long productId, String recipe) throws ServiceException {
        boolean newIsRecipe = needProductRecipe(recipe);
        try {
            productDao.updateProductRecipe(productId, newIsRecipe);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductRecipe() ", e);
            throw new ServiceException("DaoException is in method updateProductRecipe() ", e);
        }
    }

    @Override
    public void updateProductInstruction(long productId, String instruction) throws ServiceException {
        try {
            productDao.updateProductInstruction(productId, instruction);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductInstruction() ", e);
            throw new ServiceException("DaoException is in method updateProductInstruction() ", e);
        }
    }

    private boolean needProductRecipe(String recipe) {
        return recipe.equals(NEED_RECIPE);
    }

    private String convertProductRecipe(boolean recipe) {
        if (recipe) {
            return NEED_RECIPE;
        } else {
            return DONT_NEED_RECIPE;
        }
    }

}

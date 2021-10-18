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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private Logger logger = LogManager.getLogger();
    private static final String NEED_RECIPE_EN = "Yes";
    private static final String NEED_RECIPE_RU = "Да";
    private static final String DONT_NEED_RECIPE_EN = "No";
    private static final int RECORD_PER_PAGE = 5;
    private static final String EMPTY_STRING = "\s";
    private ProductDao productDao = ProductDaoImpl.getInstance();
    private ProductValidator productValidator = ProductValidatorImpl.getInstance();

    private ProductServiceImpl() {
    }

    private static ProductServiceImpl instance = new ProductServiceImpl();

    public static ProductServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<ProductDto> createProduct(String name, String nonProprietaryName, String dose, String group,
                                          String plant, String price,
                                          String recipe, String instruction) throws ServiceException {
        List<Product> currentProducts;
        boolean needRecipe = needProductRecipe(recipe);
        Product product = new Product.Builder()
                .setName(name)
                .setNonProprietaryName(nonProprietaryName)
                .setDose(dose)
                .setPlant(plant)
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
                currentProducts = productDao.findListProducts(pages * RECORD_PER_PAGE - RECORD_PER_PAGE);
            } else {
                currentProducts = productDao.findListProducts(pages * RECORD_PER_PAGE);
            }

        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createProduct() while find list products ", e);
            throw new ServiceException("DaoException is in method createProduct() while find list products ", e);
        }
        List<ProductDto> products = convertListProductToListProductDto(currentProducts);
        return products;
    }

    @Override
    public Map<String, String> isValidParameters(String name, String dose, String plant,
                                                 String group, String price, String instruction) {
        Map<String, String> productParameters = new HashMap<>();
        productParameters.put(RequestParameter.NAME, name);
        productParameters.put(RequestParameter.DOSE, dose);
        productParameters.put(RequestParameter.PLANT, plant);
        productParameters.put(RequestParameter.GROUP, group);
        productParameters.put(RequestParameter.PRICE, price);
        productParameters.put(RequestParameter.INSTRUCTION, instruction);
        productValidator.isValidForm(productParameters);
        return productParameters;
    }

    @Override
    public boolean isValidNonProprietaryName(String nonProprietaryName) {
       return productValidator.isValidNonProprietyName(nonProprietaryName);
    }

    @Override
    public void addPathToPicture(long id, String fileName) throws ServiceException {
        try {
            productDao.addPathToPicture(id, fileName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method addPathToPicture() while add path to picture ", e);
            throw new ServiceException("DaoException is in method addPathToProduct() while add path to picture ", e);
        }
    }

    @Override
    public String findPathToPicture(String productId) throws ServiceException {
        long id = Long.parseLong(productId);
        Optional<String> pathToPicture;
        try {
            pathToPicture = productDao.findPathToPicture(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPathToPicture() while find path to picture ", e);
            throw new ServiceException("DaoException is in method findPathToProduct() while find path to picture ", e);
        }
        String path = pathToPicture.orElse(EMPTY_STRING);
        return path;
    }

    @Override
    public List<ProductDto> findListProducts(int startingProduct) throws ServiceException {
        List<Product> productsDb;
        try {
            productsDb = productDao.findListProducts(startingProduct);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListProducts() while find list products ", e);
            throw new ServiceException("DaoException is in method findListProducts() while find list products ", e);
        }
        List<ProductDto> products = convertListProductToListProductDto(productsDb);
        return products;
    }

    @Override
    public List<ProductDto> findListProductsByName(String productName) throws ServiceException {
        List<Product> productsDb;
        try {
            productsDb = productDao.findListProductsByName(productName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListProductsByName() ", e);
            throw new ServiceException("DaoException is in method findListProductsByName() ", e);
        }
        List<ProductDto> products = convertListProductToListProductDto(productsDb);
        return products;
    }

    @Override
    public List<ProductDto> findListProductsByNonProprietaryName(String nonProprietaryName) throws ServiceException {
        List<Product> productsDb;
        try {
            productsDb = productDao.findListProductsByNonProprietaryName(nonProprietaryName);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListProductsByNonProprietaryName() ", e);
            throw new ServiceException("DaoException is in method findListProductsByNonProprietaryName() ", e);
        }
        List<ProductDto> products = convertListProductToListProductDto(productsDb);
        return products;
    }

    @Override
    public int findCurrentPage() throws ServiceException {
        int productsNumber;
        int currentPage;
        try {
            productsNumber = productDao.findProductsNumber();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findCurrentPage() while find products number() ", e);
            throw new ServiceException("DaoException is in method findCurrentPage() while find products number() ", e);
        }
        if (productsNumber % RECORD_PER_PAGE == 0) {
            currentPage = productsNumber / RECORD_PER_PAGE;
        } else {
            currentPage = productsNumber / RECORD_PER_PAGE + 1;
        }
        return currentPage;
    }

    @Override
    public ProductDto findProductById(String id) throws ServiceException {
        Optional<Product> product;
        Product productDb;
        try {
            long productId = Long.parseLong(id);
            product = productDao.findProductById(productId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findProductById() ", e);
            throw new ServiceException("DaoException is in method findProductById() ", e);
        }
        productDb = product.get();
        ProductDto productDto = convertProductToProductDto(productDb);
        return productDto;
    }

    @Override
    public Map<Product, Integer> addProductToOrder(String id, Map<Product, Integer> order) throws ServiceException {
        Optional<Product> product;
        try {
            long productId = Long.parseLong(id);
            product = productDao.findProductForOrderById(productId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method addProductToOrder() while find product for order by id ", e);
            throw new ServiceException("DaoException is in method addProductToOrder() while find product for order by id ", e);
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
            logger.log(Level.ERROR, "DaoException is in method findInstructionByProductId() while find product by id ", e);
            throw new ServiceException("DaoException is in method findInstructionByProductId() while find product by id ", e);
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
    public void updateProductNonProprietaryName(long productId, String nonProprietaryName) throws ServiceException {
        try {
            productDao.updateProductNonProprietaryName(productId, nonProprietaryName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductNonProprietaryName() ", e);
            throw new ServiceException("DaoException is in method updateProductNonProprietaryName() ", e);
        }
    }

    @Override
    public void updateProductDose(long productId, String dose) throws ServiceException {
        try {
            productDao.updateProductDose(productId, dose);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductDose() ", e);
            throw new ServiceException("DaoException is in method updateProductDose() ", e);
        }
    }

    @Override
    public void updateProductPlant(long productId, String plant) throws ServiceException {
        try {
            productDao.updateProductPlant(productId, plant);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductPlant() ", e);
            throw new ServiceException("DaoException is in method updateProductPlant() ", e);
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
        if (recipe.equals(NEED_RECIPE_EN)) {
            return true;
        }
        return recipe.equals(NEED_RECIPE_RU);
    }

    private String convertProductRecipe(boolean recipe) {
        if (recipe) {
            return NEED_RECIPE_EN;
        } else {
            return DONT_NEED_RECIPE_EN;
        }
    }

    private List<ProductDto> convertListProductToListProductDto(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDto.Builder()
                        .setProductId(product.getProductId())
                        .setName(product.getName())
                        .setNonProprietaryName(product.getNonProprietaryName())
                        .setDose(product.getDose())
                        .setPlant(product.getPlant())
                        .setGroup(product.getGroup())
                        .setPrice(product.getPrice())
                        .setRecipe(convertProductRecipe(product.isRecipe()))
                        .setInstruction(product.getInstruction())
                        .build()).collect(Collectors.toList());

    }

    private ProductDto convertProductToProductDto(Product productDb) {
        return  new ProductDto.Builder()
                .setName(productDb.getName())
                .setNonProprietaryName(productDb.getNonProprietaryName())
                .setDose(productDb.getDose())
                .setPlant(productDb.getPlant())
                .setGroup(productDb.getGroup())
                .setPrice(productDb.getPrice())
                .setRecipe(convertProductRecipe(productDb.isRecipe()))
                .setInstruction(productDb.getInstruction())
                .build();
    }
}

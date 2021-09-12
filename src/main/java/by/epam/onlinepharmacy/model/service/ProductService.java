package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    void createProduct(String name, String group, String price, String recipe, String instruction) throws ServiceException;
    Map<String, String> isValidParameters(String name, String group, String price, String instruction);
    void addPathToPicture(long id, String fileName) throws ServiceException;
    List<Product> findAllProducts() throws ServiceException;
    Optional<String> findPathToPicture(long id) throws ServiceException;
    Optional<Product> findProductById(String id) throws ServiceException;
    Map<Product, Integer> addProductToOrder(String id, Map<Product, Integer> order) throws ServiceException;
    void updateProductQuantityInOrder(String productId, String quantity, Map<Product, Integer> order);

}

package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.io.File;
import java.util.List;

public interface ProductService {
    void createProduct(String name, String group, String price, String recipe, String instruction) throws ServiceException;
    void addPicture(long id, String fileName);
    List<Product> findAllProducts() throws ServiceException;
    String find(long id);
}

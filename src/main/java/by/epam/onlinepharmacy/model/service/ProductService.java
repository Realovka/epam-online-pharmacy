package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

public interface ProductService {
    void createProduct(Product product) throws DaoException;
}

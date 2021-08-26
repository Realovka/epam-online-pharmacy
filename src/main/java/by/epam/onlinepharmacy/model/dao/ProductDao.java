package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

public interface ProductDao {
    void createProduct(Product product) throws DaoException;
}

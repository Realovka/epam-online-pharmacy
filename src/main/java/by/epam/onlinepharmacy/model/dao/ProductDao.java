package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

import java.io.File;

public interface ProductDao {
    void createProduct(Product product) throws DaoException;
    void addPicture(File file) throws DaoException;
}

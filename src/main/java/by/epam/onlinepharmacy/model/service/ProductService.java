package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

import java.io.File;

public interface ProductService {
    void createProduct(String name, String group, String price, String recipe, String instruction) throws DaoException;
    void addPicture(File file);
}

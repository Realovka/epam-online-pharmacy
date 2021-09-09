package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

import java.io.File;
import java.util.List;

public interface ProductDao {
    void createProduct(Product product) throws DaoException;
    void addPicture(long id, String fileNAme) throws DaoException;
    List<Product> findAllProducts() throws DaoException;
    String findPic(long id) throws DaoException;;
}

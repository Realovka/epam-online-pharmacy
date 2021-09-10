package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    void createProduct(Product product) throws DaoException;
    void addPathToPicture(long id, String fileNAme) throws DaoException;
    List<Product> findAllProducts() throws DaoException;
    Optional<Product> findProductForOrderById(long id) throws DaoException;
    Optional<String> findPathToPicture(long id) throws DaoException;
    Optional<Product> findProductById(long id) throws DaoException;
}

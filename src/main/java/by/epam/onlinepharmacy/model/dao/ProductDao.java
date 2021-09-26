package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    void createProduct(Product product) throws DaoException;
    void addPathToPicture(long id, String fileName) throws DaoException;
    List<Product> findListProducts(int startingProduct) throws DaoException;
    List<Product> findListProductsByName(String productName) throws DaoException;
    List<Product> findListProductsByNonProprietaryName(String nonProprietaryName) throws DaoException;
    int findProductsNumber() throws DaoException;
    Optional<Product> findProductForOrderById(long id) throws DaoException;
    Optional<String> findPathToPicture(long id) throws DaoException;
    Optional<Product> findProductById(long id) throws DaoException;
    void updateProductName(long id, String name) throws DaoException;
    void updateProductNonProprietaryName(long id, String nonProprietaryName) throws DaoException;
    void updateProductDose(long id, String dose) throws DaoException;
    void updateProductPlant(long id, String plant) throws DaoException;
    void updateProductGroup(long id, String group) throws DaoException;
    void updateProductPrice(long id, BigDecimal price) throws DaoException;
    void updateProductRecipe(long id, boolean recipe) throws DaoException;
    void updateProductInstruction(long id, String instruction) throws DaoException;
}

package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Product dao.
 */
public interface ProductDao {
    /**
     * Create product int.
     *
     * @param product the product
     * @return the int
     * @throws DaoException the dao exception
     */
    int createProduct(Product product) throws DaoException;

    /**
     * Add path to picture int.
     *
     * @param id       the id
     * @param fileName the file name
     * @return the int
     * @throws DaoException the dao exception
     */
    int addPathToPicture(long id, String fileName) throws DaoException;

    /**
     * Find list products list.
     *
     * @param startingProduct the starting product
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> findListProducts(int startingProduct) throws DaoException;

    /**
     * Find list products by name list.
     *
     * @param productName the product name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> findListProductsByName(String productName) throws DaoException;

    /**
     * Find list products by non proprietary name list.
     *
     * @param nonProprietaryName the non proprietary name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> findListProductsByNonProprietaryName(String nonProprietaryName) throws DaoException;

    /**
     * Find products number int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int findProductsNumber() throws DaoException;

    /**
     * Find product for order by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Product> findProductForOrderById(long id) throws DaoException;

    /**
     * Find path to picture optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<String> findPathToPicture(long id) throws DaoException;

    /**
     * Find product by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Product> findProductById(long id) throws DaoException;

    /**
     * Update product name int.
     *
     * @param id   the id
     * @param name the name
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductName(long id, String name) throws DaoException;

    /**
     * Update product non proprietary name int.
     *
     * @param id                 the id
     * @param nonProprietaryName the non proprietary name
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductNonProprietaryName(long id, String nonProprietaryName) throws DaoException;

    /**
     * Update product dose int.
     *
     * @param id   the id
     * @param dose the dose
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductDose(long id, String dose) throws DaoException;

    /**
     * Update product plant int.
     *
     * @param id    the id
     * @param plant the plant
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductPlant(long id, String plant) throws DaoException;

    /**
     * Update product group int.
     *
     * @param id    the id
     * @param group the group
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductGroup(long id, String group) throws DaoException;

    /**
     * Update product price int.
     *
     * @param id    the id
     * @param price the price
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductPrice(long id, BigDecimal price) throws DaoException;

    /**
     * Update product recipe int.
     *
     * @param id     the id
     * @param recipe the recipe
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductRecipe(long id, boolean recipe) throws DaoException;

    /**
     * Update product instruction int.
     *
     * @param id          the id
     * @param instruction the instruction
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateProductInstruction(long id, String instruction) throws DaoException;
}

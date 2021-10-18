package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface Product service.
 */
public interface ProductService {
    /**
     * Create product list.
     *
     * @param name               the name
     * @param nonProprietaryName the non proprietary name
     * @param dose               the dose
     * @param plant              the plant
     * @param group              the group
     * @param price              the price
     * @param recipe             the recipe
     * @param instruction        the instruction
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProductDto> createProduct(String name, String nonProprietaryName, String dose, String plant,
                                   String group, String price, String recipe, String instruction) throws ServiceException;

    /**
     * Is valid parameters map.
     *
     * @param name        the name
     * @param dose        the dose
     * @param plant       the plant
     * @param group       the group
     * @param price       the price
     * @param instruction the instruction
     * @return the map
     */
    Map<String, String> isValidParameters(String name, String dose, String plant, String group, String price, String instruction);

    /**
     * Is valid non proprietary name boolean.
     *
     * @param nonProprietaryName the non proprietary name
     * @return the boolean
     */
    boolean isValidNonProprietaryName(String nonProprietaryName);

    /**
     * Add path to picture.
     *
     * @param id       the id
     * @param fileName the file name
     * @throws ServiceException the service exception
     */
    void addPathToPicture(long id, String fileName) throws ServiceException;

    /**
     * Find list products list.
     *
     * @param startingProduct the starting product
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProductDto> findListProducts(int startingProduct) throws ServiceException;

    /**
     * Find list products by name list.
     *
     * @param productName the product name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProductDto> findListProductsByName(String productName) throws ServiceException;

    /**
     * Find list products by non proprietary name list.
     *
     * @param nonProprietaryName the non proprietary name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProductDto> findListProductsByNonProprietaryName(String nonProprietaryName) throws ServiceException;

    /**
     * Find current page int.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int findCurrentPage() throws ServiceException;

    /**
     * Find path to picture string.
     *
     * @param productId the product id
     * @return the string
     * @throws ServiceException the service exception
     */
    String findPathToPicture(String productId) throws ServiceException;

    /**
     * Find product by id product dto.
     *
     * @param id the id
     * @return the product dto
     * @throws ServiceException the service exception
     */
    ProductDto findProductById(String id) throws ServiceException;

    /**
     * Add product to order map.
     *
     * @param id    the id
     * @param order the order
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Product, Integer> addProductToOrder(String id, Map<Product, Integer> order) throws ServiceException;

    /**
     * Update product quantity in order.
     *
     * @param productId the product id
     * @param quantity  the quantity
     * @param order     the order
     */
    void updateProductQuantityInOrder(String productId, String quantity, Map<Product, Integer> order);

    /**
     * Update product name.
     *
     * @param productId the product id
     * @param name      the name
     * @throws ServiceException the service exception
     */
    void updateProductName(long productId, String name) throws ServiceException;

    /**
     * Update product non proprietary name.
     *
     * @param productId          the product id
     * @param nonProprietaryName the non proprietary name
     * @throws ServiceException the service exception
     */
    void updateProductNonProprietaryName(long productId, String nonProprietaryName) throws ServiceException;

    /**
     * Update product dose.
     *
     * @param productId the product id
     * @param dose      the dose
     * @throws ServiceException the service exception
     */
    void updateProductDose(long productId, String dose) throws ServiceException;

    /**
     * Update product plant.
     *
     * @param productId the product id
     * @param plant     the plant
     * @throws ServiceException the service exception
     */
    void updateProductPlant(long productId, String plant) throws ServiceException;

    /**
     * Update product group.
     *
     * @param productId the product id
     * @param group     the group
     * @throws ServiceException the service exception
     */
    void updateProductGroup(long productId, String group) throws ServiceException;

    /**
     * Update product price.
     *
     * @param productId the product id
     * @param price     the price
     * @throws ServiceException the service exception
     */
    void updateProductPrice(long productId, String price) throws ServiceException;

    /**
     * Update product recipe.
     *
     * @param productId the product id
     * @param recipe    the recipe
     * @throws ServiceException the service exception
     */
    void updateProductRecipe(long productId, String recipe) throws ServiceException;

    /**
     * Update product instruction.
     *
     * @param productId   the product id
     * @param instruction the instruction
     * @throws ServiceException the service exception
     */
    void updateProductInstruction(long productId, String instruction) throws ServiceException;

    /**
     * Find instruction by product id product dto.
     *
     * @param productId the product id
     * @return the product dto
     * @throws ServiceException the service exception
     */
    ProductDto findInstructionByProductId(long productId) throws ServiceException;

}

package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> createProduct(String name, String nonProprietaryName, String dose, String plant,
                                   String group, String price, String recipe, String instruction) throws ServiceException;
    Map<String, String> isValidParameters(String name, String dose, String plant, String group, String price, String instruction);
    boolean isValidNonProprietaryName(String nonProprietaryName);
    void addPathToPicture(long id, String fileName) throws ServiceException;
    List<ProductDto> findListProducts(int startingProduct) throws ServiceException;
    List<ProductDto> findListProductsByName(String productName) throws ServiceException;
    List<ProductDto> findListProductsByNonProprietaryName(String nonProprietaryName) throws ServiceException;
    int findCurrentPage() throws ServiceException;
    String findPathToPicture(String productId) throws ServiceException;
    ProductDto findProductById(String id) throws ServiceException;
    Map<Product, Integer> addProductToOrder(String id, Map<Product, Integer> order) throws ServiceException;
    void updateProductQuantityInOrder(String productId, String quantity, Map<Product, Integer> order);
    void updateProductName(long productId, String name) throws ServiceException;
    void updateProductNonProprietaryName(long productId, String nonProprietaryName) throws ServiceException;
    void updateProductDose(long productId, String dose) throws ServiceException;
    void updateProductPlant(long productId, String plant) throws ServiceException;
    void updateProductGroup(long productId, String group) throws ServiceException;
    void updateProductPrice(long productId, String price) throws ServiceException;
    void updateProductRecipe(long productId, String recipe) throws ServiceException;
    void updateProductInstruction(long productId, String instruction) throws ServiceException;
    ProductDto findInstructionByProductId(long productId) throws ServiceException;

}

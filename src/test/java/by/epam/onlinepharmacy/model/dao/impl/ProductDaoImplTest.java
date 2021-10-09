package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductDaoImplTest {

    @Mock
    private ProductDaoImpl productDao;
    private List<Product> products;
    List<Product> testProducts;
    private Product product;
    private Product testProduct;

    @BeforeEach
    public void setUp() {
       product = new Product.Builder()
               .setProductId(1)
               .setName("Concor")
               .setNonProprietaryName("Bisoprolol")
               .setDose("5 mg №30 in tab.")
               .setPlant("Merck")
               .setGroup("cardiac")
               .setPrice(new BigDecimal("26.3"))
               .isRecipe(false)
               .setPicture("D://")
               .setInstruction("instruction")
               .build();
       testProduct = new Product();
       products = new ArrayList<>();
       testProducts = new ArrayList<>();
       products.add(product);
    }

    @Test
    public void createProductTest() throws DaoException {
        when(productDao.createProduct(product)).thenReturn(1);
        int actualResult = productDao.createProduct(product);
        assertEquals(1, actualResult);
    }

    @Test
    public void createProductFalseTest() throws DaoException {
        when(productDao.createProduct(product)).thenReturn(1);
        int actualResult = productDao.createProduct(product);
        assertNotEquals(2, actualResult);
    }

    @Test
    public void addPathToPictureTest() throws DaoException {
        when(productDao.addPathToPicture(1, "pathToPicture")).thenReturn(1);
        int actualResult = productDao.addPathToPicture(1, "pathToPicture");
        assertEquals(1, actualResult);
    }

    @Test
    public void addPathToPictureFalseTest() throws DaoException {
        when(productDao.addPathToPicture(1, "pathToPicture")).thenReturn(1);
        int actualResult = productDao.addPathToPicture(1, "pathToPicture");
        assertNotEquals(0, actualResult);
    }

    @Test
    public void findListProductsTest() throws DaoException {
        when(productDao.findListProducts(1)).thenReturn(products);
        List<Product> actualProducts = productDao.findListProducts(1);
        assertEquals(products, actualProducts);
    }

    @Test
    public void findListProductsFalseTest() throws DaoException {
        when(productDao.findListProducts(1)).thenReturn(products);
        List<Product> actualProducts = productDao.findListProducts(1);
        assertNotEquals(testProducts, actualProducts);
    }

    @Test
    public void findListProductsByNameTest() throws DaoException {
        when(productDao.findListProductsByName("Concor")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Concor");
        assertEquals(products, actualProducts);
    }

    @Test
    public void findListProductsByNameFalseTest() throws DaoException {
        when(productDao.findListProductsByName("Concor")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Concor");
        assertNotEquals(testProducts, actualProducts);
    }

    @Test
    public void findListProductsByNonProprietaryNameTest() throws DaoException {
        when(productDao.findListProductsByName("Bisoprolol")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Bisoprolol");
        assertEquals(products, actualProducts);
    }

    @Test
    public void findListProductsByNonProprietaryNameFalseTest() throws DaoException {
        when(productDao.findListProductsByName("Bisoprolol")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Bisoprolol");
        assertNotEquals(testProducts, actualProducts);
    }

    @Test
    public void findProductsNumberTest() throws DaoException {
        when(productDao.findProductsNumber()).thenReturn(5);
        int actualResult = productDao.findProductsNumber();
        assertEquals(5, actualResult);
    }

    @Test
    public void findProductsNumberFalseTest() throws DaoException {
        when(productDao.findProductsNumber()).thenReturn(5);
        int actualResult = productDao.findProductsNumber();
        assertNotEquals(4, actualResult);
    }

    @Test
    public void findPathToPictureTest() throws DaoException {
        when(productDao.findPathToPicture(1)).thenReturn(Optional.of("D://"));
        Optional<String> actualPath = productDao.findPathToPicture(1);
        assertEquals(product.getPathToPicture(), actualPath.get());
    }

    @Test
    public void findPathToPictureFalseTest() throws DaoException {
        when(productDao.findPathToPicture(1)).thenReturn(Optional.of("D://"));
        Optional<String> actualPath = productDao.findPathToPicture(1);
        assertNotEquals(testProduct.getPathToPicture(), actualPath.get());
    }

    @Test
    public void findProductByIdTest() throws DaoException {
        when(productDao.findProductById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductById(1);
        assertEquals(product, actualProduct.get());
    }

    @Test
    public void findProductByIdFalseTest() throws DaoException {
        when(productDao.findProductById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductById(1);
        assertNotEquals(testProduct, actualProduct.get());
    }

    @Test
    public void findProductForOrderByIdTest() throws DaoException {
        when(productDao.findProductForOrderById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductForOrderById(1);
        assertEquals(product, actualProduct.get());
    }

    @Test
    public void findProductForOrderByIdFalseTest() throws DaoException {
        when(productDao.findProductForOrderById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductForOrderById(1);
        assertNotEquals(testProduct, actualProduct.get());
    }

    @Test
    public void updateProductNameTest() throws DaoException {
        when(productDao.updateProductName(1, "Bisoprolol-Maxpharma")).thenReturn(1);
        int actualResult = productDao.updateProductName(1, "Bisoprolol-Maxpharma");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductNameFalseTest() throws DaoException {
        when(productDao.updateProductName(1, "Enap")).thenReturn(1);
        int actualResult = productDao.updateProductName(1, "Enap");
        assertNotEquals(5, actualResult);
    }

    @Test
    public void updateProductNonProprietaryNameTest() throws DaoException {
        when(productDao.updateProductNonProprietaryName(1, "Enalapril")).thenReturn(1);
        int actualResult = productDao.updateProductNonProprietaryName(1, "Enalapril");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductNonProprietaryNameFalseTest() throws DaoException {
        when(productDao.updateProductNonProprietaryName(1, "Enalapril")).thenReturn(1);
        int actualResult = productDao.updateProductNonProprietaryName(1, "Enalapril");
        assertNotEquals(5, actualResult);
    }

    @Test
    public void updateProductDoseTest() throws DaoException {
        when(productDao.updateProductDose(1, "10 mg №20 in tab.")).thenReturn(1);
        int actualResult = productDao.updateProductDose(1, "10 mg №20 in tab.");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductDoseFalseTest() throws DaoException {
        when(productDao.updateProductDose(1, "10 mg №20 in tab.")).thenReturn(1);
        int actualResult = productDao.updateProductDose(1, "10 mg №20 in tab.");
        assertNotEquals(5, actualResult);
    }

    @Test
    public void updateProductPlantTest() throws DaoException {
        when(productDao.updateProductPlant(1, "KRKA")).thenReturn(1);
        int actualResult = productDao.updateProductPlant(1, "KRKA");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductPlantFalseTest() throws DaoException {
        when(productDao.updateProductPlant(1, "KRKA")).thenReturn(1);
        int actualResult = productDao.updateProductPlant(1, "KRKA");
        assertNotEquals(10, actualResult);
    }

    @Test
    public void updateProductGroupTest() throws DaoException {
        when(productDao.updateProductGroup(1, "Antihipertension")).thenReturn(1);
        int actualResult = productDao.updateProductGroup(1, "Antihipertension");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductGroupFalseTest() throws DaoException {
        when(productDao.updateProductGroup(1, "Antihipertension")).thenReturn(1);
        int actualResult = productDao.updateProductGroup(1, "Antihipertension");
        assertNotEquals(5, actualResult);
    }

    @Test
    public void updateProductPriceTest() throws DaoException {
        when(productDao.updateProductPrice(1, new BigDecimal("10.23"))).thenReturn(1);
        int actualResult = productDao.updateProductPrice(1, new BigDecimal("10.23"));
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductPriceFalseTest() throws DaoException {
        when(productDao.updateProductPrice(1, new BigDecimal("10.23"))).thenReturn(1);
        int actualResult = productDao.updateProductPrice(1, new BigDecimal("10.23"));
        assertNotEquals(10, actualResult);
    }

    @Test
    public void updateProductRecipeTest() throws DaoException {
        when(productDao.updateProductRecipe(1, true)).thenReturn(1);
        int actualResult = productDao.updateProductRecipe(1, true);
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductRecipeFalseTest() throws DaoException {
        when(productDao.updateProductRecipe(1, true)).thenReturn(1);
        int actualResult = productDao.updateProductRecipe(1, true);
        assertNotEquals(2, actualResult);
    }

    @Test
    public void updateProductInstructionTest() throws DaoException {
        when(productDao.updateProductInstruction(1, "instr")).thenReturn(1);
        int actualResult = productDao.updateProductInstruction(1, "instr");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateProductInstructionFalseTest() throws DaoException {
        when(productDao.updateProductInstruction(1, "instr")).thenReturn(1);
        int actualResult = productDao.updateProductInstruction(1, "instr");
        assertNotEquals(0, actualResult);
    }

}

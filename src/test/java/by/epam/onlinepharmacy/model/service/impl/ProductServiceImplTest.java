package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductServiceImpl productService;
    private List<ProductDto> products;
    private List<ProductDto> productsTest;
    private ProductDto firstProduct;
    private ProductDto secondProduct;

    @BeforeEach
    public void setUp() {
        products = new ArrayList<>();
        productsTest = new ArrayList<>();
        firstProduct = new ProductDto.Builder()
                .setProductId(1)
                .setName("Concor")
                .setNonProprietaryName("Bisoprolol")
                .setInstruction("Instruction of concor")
                .build();
        secondProduct = new ProductDto.Builder()
                .setProductId(2)
                .setName("Bisocard")
                .setNonProprietaryName("Bisoprolol")
                .setInstruction("Instruction of bisoprolol")
                .build();
        products.add(firstProduct);
        products.add(secondProduct);
        productsTest.add(firstProduct);
    }

    @Test
    public void createProductTest() throws ServiceException {
        when(productService.createProduct("Concor", "Bisoprolol",
                "5 mg № 30", "Cardiac", "Merc",
                "22.30", "No", "Instr")).thenReturn(products);
        List<ProductDto> actualResult = productService.createProduct("Concor",
                "Bisoprolol", "5 mg № 30", "Cardiac", "Merc",
                "22.30", "No", "Instr");
        assertEquals(products, actualResult);
    }

    @Test
    public void createProductNotEqualsTest() throws ServiceException {
        when(productService.createProduct("Concor", "Bisoprolol",
                "5 mg № 30", "Cardiac", "Merc",
                "22.30", "No", "Instr")).thenReturn(products);
        List<ProductDto> actualResult = productService.createProduct("Concor",
                "Bisoprolol", "5 mg № 30", "Cardiac", "Merc",
                "22.30", "No", "Instr");
        assertNotEquals(productsTest, actualResult);
    }

    @Test
    public void findListProductsTest() throws ServiceException {
        when(productService.findListProducts(5)).thenReturn(products);
        List<ProductDto> productsActual = productService.findListProducts(5);
        assertEquals(products, productsActual);
    }

    @Test
    public void findListProductsNotEqualsTest() throws ServiceException {
        when(productService.findListProducts(5)).thenReturn(products);
        List<ProductDto> productsActual = productService.findListProducts(5);
        assertNotEquals(productsTest, productsActual);
    }

    @Test
    public void findListProductsByNameTest() throws ServiceException {
        when(productService.findListProductsByName("Concor")).thenReturn(productsTest);
        List<ProductDto> actualResult = productService.findListProductsByName("Concor");
        assertEquals(productsTest, actualResult);
    }

    @Test
    public void findListProductsByNameNotEqualsTest() throws ServiceException {
        when(productService.findListProductsByName("Concor")).thenReturn(productsTest);
        List<ProductDto> actualResult = productService.findListProductsByName("Concor");
        assertNotEquals(products, actualResult);
    }

    @Test
    public void findListProductsByNonProprietaryNameTest() throws ServiceException {
        when(productService.findListProductsByNonProprietaryName("Concor")).thenReturn(productsTest);
        List<ProductDto> actualResult = productService.findListProductsByNonProprietaryName("Concor");
        assertEquals(productsTest, actualResult);
    }

    @Test
    public void findListProductsByNonProprietaryNameNotEqualsTest() throws ServiceException {
        when(productService.findListProductsByNonProprietaryName("Concor")).thenReturn(productsTest);
        List<ProductDto> actualResult = productService.findListProductsByNonProprietaryName("Concor");
        assertNotEquals(products, actualResult);
    }

    @Test
    public void findCurrentPageTest() throws ServiceException {
        when(productService.findCurrentPage()).thenReturn(5);
        int actualResult = productService.findCurrentPage();
        assertEquals(5, actualResult);
    }

    @Test
    public void findCurrentPageNotEqualsTest() throws ServiceException {
        when(productService.findCurrentPage()).thenReturn(5);
        int actualResult = productService.findCurrentPage();
        assertNotEquals(6, actualResult);
    }

    @Test
    public void findProductByIdTest() throws ServiceException {
        when(productService.findProductById("1")).thenReturn(firstProduct);
        ProductDto product = productService.findProductById("1");
        assertEquals(firstProduct, product);
    }

    @Test
    public void findProductByIdNotEqualsTest() throws ServiceException {
        when(productService.findProductById("1")).thenReturn(firstProduct);
        ProductDto product = productService.findProductById("1");
        assertNotEquals(secondProduct, product);
    }

    @Test
    public void findInstructionByProductIdTest() throws ServiceException {
        when(productService.findProductForInstructionByProductId(1)).thenReturn(firstProduct);
        ProductDto product = productService.findProductForInstructionByProductId(1);
        assertEquals(firstProduct, product);
    }

    @Test
    public void findInstructionByProductIdNotEqualsTest() throws ServiceException {
        when(productService.findProductForInstructionByProductId(1)).thenReturn(firstProduct);
        ProductDto product = productService.findProductForInstructionByProductId(1);
        assertNotEquals(secondProduct, product);
    }
}

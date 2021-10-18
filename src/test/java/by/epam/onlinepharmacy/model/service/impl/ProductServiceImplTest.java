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

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductServiceImpl productService;
    private List<ProductDto> products;
    private ProductDto firstProduct;
    private ProductDto secondProduct;

    @BeforeEach
    public void setUp() {
        products = new ArrayList<>();
        firstProduct = new ProductDto.Builder()
                .setProductId(1)
                .build();
        secondProduct = new ProductDto.Builder()
                .setProductId(2)
                .build();
        products.add(firstProduct);
        products.add(secondProduct);
    }

    @Test
    public void createProductTest() throws ServiceException {
        when(productService.createProduct("Concor", "Bisoprolol", "5 mg № 30", "Cardiac", "Merc",
                "22.30", "No", "Instr")).thenReturn(products);
        List<ProductDto> actualResult = productService.createProduct("Concor", "Bisoprolol", "5 mg № 30", "Cardiac", "Merc",
                "22.30", "No", "Instr");
        assertEquals(products, actualResult);
    }
}

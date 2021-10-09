package by.epam.onlinepharmacy.model.validation.impl;

import by.epam.onlinepharmacy.model.validation.BasketValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketValidatorImplTest {
    private BasketValidator basketValidator;
    private String quantity;
    private String negativeQuantity;
    private String fractionalQuantity;
    private String zero;
    private String word;

    @BeforeEach
    public void setUp() {
        basketValidator = BasketValidatorImpl.getInstance();
        quantity = "5";
        negativeQuantity = "-5";
        fractionalQuantity = "0.0";
        zero = "0";
        word = "parameter";
    }

    @Test
    public void isValidProductQuantityTest() {
        boolean result = basketValidator.isValidProductQuantity(quantity);
        assertTrue(result);
    }

    @Test
    public void isValidProductQuantityZeroTest() {
        boolean result = basketValidator.isValidProductQuantity(zero);
        assertTrue(result);
    }

    @Test
    public void isValidProductQuantityFalseNegativeQuantityTest() {
        boolean result = basketValidator.isValidProductQuantity(negativeQuantity);
        assertFalse(result);
    }

    @Test
    public void isValidProductQuantityFalseFractionalQuantityTest() {
        boolean result = basketValidator.isValidProductQuantity(fractionalQuantity);
        assertFalse(result);
    }

    @Test
    public void isValidProductQuantityFalseWordTest() {
        boolean result = basketValidator.isValidProductQuantity(word);
        assertFalse(result);
    }
}

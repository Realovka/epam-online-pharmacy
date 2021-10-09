package by.epam.onlinepharmacy.model.validation.impl;

import by.epam.onlinepharmacy.model.validation.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductValidatorImplTest {
    private ProductValidator productValidator;
    private String correctParameter;
    private String emptyString;
    private String longString;
    private String correctPrice;
    private String zero;
    private String negativeNumber;
    private Map<String, String> data;

    @BeforeEach
    public void setUp() {
        productValidator = ProductValidatorImpl.getInstance();
        correctParameter = "parameter";
        emptyString = "\s";
        correctPrice = "15.30";
        zero = "0";
        negativeNumber = "-5.00";
        data = new LinkedHashMap<>();
        data.put("name", "name");
        data.put("dose", "dose");
        data.put("group", "group");
        data.put("plant", "plant");
        data.put("price", "15.98");
        data.put("instruction", "instruction");
        longString = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                qui officia deserunt mollit anim id est laborum.
                """;
    }

    @Test
    public void isValidFormTest() {
       boolean result = productValidator.isValidForm(data);
       assertTrue(result);
    }

    @Test
    public void isValidFormFalseNameEmptyTest() {
        data.put("name", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseNameLongTest() {
        data.put("name", longString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseDoseEmptyTest() {
        data.put("dose", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseDoseLongTest() {
        data.put("dose", longString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseGroupEmptyTest() {
        data.put("group", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseGroupLongTest() {
        data.put("group", longString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalsePlantEmptyTest() {
        data.put("plant", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalsePlantLongTest() {
        data.put("plant", longString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalsePriceEmptyTest() {
        data.put("price", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalsePriceIncorrectTest() {
        data.put("price", negativeNumber);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseInstructionEmptyTest() {
        data.put("instruction", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidStringParameterTest() {
        boolean result = productValidator.isValidStringParameters(correctParameter);
        assertTrue(result);
    }

    @Test
    public void isValidStringParameterFalseEmptyParameterTest() {
        boolean result = productValidator.isValidStringParameters(emptyString);
        assertFalse(result);
    }

    @Test
    public void isValidStringParameterFalseLongParameterTest() {
        boolean result = productValidator.isValidStringParameters(longString);
        assertFalse(result);
    }

    @Test
    public void isValidNonProprietyNameTest() {
        boolean result = productValidator.isValidNonProprietyName(correctParameter);
        assertTrue(result);
    }

    @Test
    public void isValidNonProprietyNameEmptyStringTest() {
        boolean result = productValidator.isValidNonProprietyName(emptyString);
        assertTrue(result);
    }

    @Test
    public void isValidNonProprietyNameFalseLongStringTest() {
        boolean result = productValidator.isValidNonProprietyName(longString);
        assertFalse(result);
    }

    @Test
    public void isValidPriceTest() {
        boolean result = productValidator.isValidPrice(correctPrice);
        assertTrue(result);
    }

    @Test
    public void isValidPriceParameterZeroTest() {
        boolean result = productValidator.isValidPrice(zero);
        assertTrue(result);
    }

    @Test
    public void isValidPriceFalseTextParameterTest() {
        boolean result = productValidator.isValidPrice(correctParameter);
        assertFalse(result);
    }

    @Test
    public void isValidPriceFalseNegativeNumberTest() {
        boolean result = productValidator.isValidPrice(negativeNumber);
        assertFalse(result);
    }

    @Test
    public void isValidPriceFalseEmptyStringTest() {
        boolean result = productValidator.isValidPrice(emptyString);
        assertFalse(result);
    }

    @Test
    public void isValidInstructionTest() {
        boolean result = productValidator.isValidInstruction(longString);
        assertTrue(result);
    }

    @Test
    public void isValidInstructionFalseTest() {
        boolean result = productValidator.isValidInstruction(emptyString);
        assertFalse(result);
    }
}

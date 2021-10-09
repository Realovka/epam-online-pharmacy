package by.epam.onlinepharmacy.model.validation.impl;

import by.epam.onlinepharmacy.model.validation.PharmacyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PharmacyValidatorImplTest {
    private static final String BLOCK = "block";
    private PharmacyValidator pharmacyValidator;
    private Map<String, String> mapData;
    private String number;
    private String incorrectNumber;
    private String longNumber;
    private String negativeNumber;
    private String zero;
    private String emptyString;
    private String longText;
    private String city;

    @BeforeEach
    public void setUp() {
        pharmacyValidator = PharmacyValidatorImpl.getInstance();
        number = "10";
        city = "Brest";
        incorrectNumber = "Hello";
        longNumber = "158621431845632";
        negativeNumber = "-2";
        zero = "0";
        emptyString = "\s";
        mapData = new LinkedHashMap<>();
        mapData.put(BLOCK, number);
        longText = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                qui officia deserunt mollit anim id est laborum.
                """;

    }

    @Test
    public void isValidNumberTest() {
        boolean actualResult = pharmacyValidator.isValidNumber(number);
        assertTrue(actualResult);
    }

    @Test
    public void isValidNumberFalseTest() {
        boolean actualResult = pharmacyValidator.isValidNumber(incorrectNumber);
        assertFalse(actualResult);
    }

    @Test
    public void isValidNumberLongNumberFalseTest() {
        boolean actualResult = pharmacyValidator.isValidNumber(longNumber);
        assertFalse(actualResult);
    }

    @Test
    public void isValidNumberNegativeNumberFalseTest() {
        boolean actualResult = pharmacyValidator.isValidNumber(negativeNumber);
        assertFalse(actualResult);
    }

    @Test
    public void isValidNumberZeroFalseTest() {
        boolean actualResult = pharmacyValidator.isValidNumber(zero);
        assertFalse(actualResult);
    }

    @Test
    public void isValidNumberEmptyStringFalseTest() {
        boolean actualResult = pharmacyValidator.isValidNumber(emptyString);
        assertFalse(actualResult);
    }

    @Test
    public void isValidCityOrStreetTest() {
        boolean actualResult = pharmacyValidator.isValidCityOrStreet(city);
        assertTrue(actualResult);
    }

    @Test
    public void isValidCityOrStreetEmptyStringFalseTest() {
        boolean actualResult = pharmacyValidator.isValidCityOrStreet(emptyString);
        assertFalse(actualResult);
    }

    @Test
    public void isValidCityOrStreetLongTextFalseTest() {
        boolean actualResult = pharmacyValidator.isValidCityOrStreet(longText);
        assertFalse(actualResult);
    }

    @Test
    public void isValidHouseTest() {
        boolean actualResult = pharmacyValidator.isValidHouse(number);
        assertTrue(actualResult);
    }

    @Test
    public void isValidHouseEmptyStringFalseTest() {
        boolean actualResult = pharmacyValidator.isValidHouse(emptyString);
        assertFalse(actualResult);
    }

    @Test
    public void isValidHouseLongTextFalseTest() {
        boolean actualResult = pharmacyValidator.isValidHouse(longText);
        assertFalse(actualResult);
    }

    @Test
    public void isValidBlockTest() {
        boolean actualResult = pharmacyValidator.isValidBlock(mapData);
        assertTrue(actualResult);
    }

    @Test
    public void isValidBlockZeroTest() {
        mapData.put(BLOCK, zero);
        boolean actualResult = pharmacyValidator.isValidBlock(mapData);
        assertTrue(actualResult);
    }

    @Test
    public void isValidBlockEmptyStringTest() {
        mapData.put(BLOCK, emptyString);
        boolean actualResult = pharmacyValidator.isValidBlock(mapData);
        assertTrue(actualResult);
    }

    @Test
    public void isValidBlockNotNumberFalseTest() {
        mapData.put(BLOCK, incorrectNumber);
        boolean actualResult = pharmacyValidator.isValidBlock(mapData);
        assertFalse(actualResult);
    }

    @Test
    public void isValidBlockNegativeNumberFalseTest() {
        mapData.put(BLOCK, negativeNumber);
        boolean actualResult = pharmacyValidator.isValidBlock(mapData);
        assertFalse(actualResult);
    }

    @Test
    public void isValidNewBlockTest() {
        boolean actualResult = pharmacyValidator.isValidNewBlock(number);
        assertTrue(actualResult);
    }

    @Test
    public void isValidNewBlockZeroTest() {
        boolean actualResult = pharmacyValidator.isValidNewBlock(zero);
        assertTrue(actualResult);
    }

    @Test
    public void isValidNewBlockEmptyStringTest() {
        boolean actualResult = pharmacyValidator.isValidNewBlock(emptyString);
        assertTrue(actualResult);
    }

    @Test
    public void isValidNewBlockNotNumberFalseTest() {
        boolean actualResult = pharmacyValidator.isValidNewBlock(incorrectNumber);
        assertFalse(actualResult);
    }

    @Test
    public void isValidNewBlockNegativeNumberFalseTest() {
        boolean actualResult = pharmacyValidator.isValidNewBlock(negativeNumber);
        assertFalse(actualResult);
    }
}

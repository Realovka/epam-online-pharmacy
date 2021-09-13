package by.epam.onlinepharmacy.model.validation;

import java.util.Map;

public interface PharmacyValidator {
    boolean isValidForm(Map<String, String> formData);
    boolean isValidNumber(String number);
    boolean isValidCityOrStreet(String parameter);
    boolean isValidHouse(String house);
    boolean isValidBlock(Map<String, String> formData);
    boolean isValidNewBlock(String block);
}

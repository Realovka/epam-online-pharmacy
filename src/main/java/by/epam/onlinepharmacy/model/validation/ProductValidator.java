package by.epam.onlinepharmacy.model.validation;

import java.util.Map;

public interface ProductValidator {
    boolean isValidForm(Map<String, String> formData);
    boolean isValidStringParameters(String parameter);
    boolean isValidNonProprietyName(String nonProprietyName);
    boolean isValidPrice(String price);
    boolean isValidInstruction(String instruction);
}

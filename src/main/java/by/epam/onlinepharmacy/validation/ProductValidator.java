package by.epam.onlinepharmacy.validation;

import java.util.Map;

public interface ProductValidator {
    boolean isValidForm(Map<String, String> formData);
    boolean isValidNameOrGroup(String parameter);
    boolean isValidPrice(String price);
    boolean isValidInstruction(String instruction);
}

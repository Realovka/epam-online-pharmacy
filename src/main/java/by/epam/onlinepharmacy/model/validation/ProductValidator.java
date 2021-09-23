package by.epam.onlinepharmacy.model.validation;

import java.util.Map;

public interface ProductValidator {
    boolean isValidForm(Map<String, String> formData);
    boolean isValidStringParameters(String parameter);
    boolean isValidInpn(String inpn);
    boolean isValidPrice(String price);
    boolean isValidInstruction(String instruction);
}

package by.epam.onlinepharmacy.validation;

import java.util.Map;

public interface PharmacyValidator {
    boolean isValidForm(Map<String, String> formData);
}

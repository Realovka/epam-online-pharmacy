package by.epam.onlinepharmacy.model.validation;

import java.util.Map;

public interface UserValidator {
    boolean isValidForm(Map<String,String> formData);
    boolean isValidStringParameter(String parameter);
    boolean isValidEmailRegistrationUser(String email);
    boolean isValidTelephoneRegistrationUser(String telephone);
}

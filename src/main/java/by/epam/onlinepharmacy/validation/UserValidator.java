package by.epam.onlinepharmacy.validation;

public class UserValidator {
    private static final String EMAIL_VALIDATOR = "^[^.][\\w.!#$%&’*+/=?^_`{|}~-]+@\\w+?\\.\\w+$";
    private static final String TELEPHONE_VALIDATOR = "^\\+[0-9]{12}$";
    private static final int MAX_SYMBOLS_DATA_USER = 45;

    public static boolean isValidAllParametersRegistrationUser(String login, String password, String firstName,
                                                  String lastName, String email, String telephone) {
        if(login.isBlank() || password.isBlank() || firstName.isBlank()
        || lastName.isBlank() || email.isBlank() || telephone.isBlank() ||
        login.length() > MAX_SYMBOLS_DATA_USER || password.length() > MAX_SYMBOLS_DATA_USER
        || firstName.length() > MAX_SYMBOLS_DATA_USER || lastName.length() > MAX_SYMBOLS_DATA_USER
        || email.length() > MAX_SYMBOLS_DATA_USER || telephone.length() > MAX_SYMBOLS_DATA_USER) {
          return false;
        }
        return true;
    }

    public static boolean isValidEmailRegistrationUser(String email) {
      return email.matches(EMAIL_VALIDATOR);
    }

    public static boolean isValidTelephoneRegistrationUser(String telephone) {
        return telephone.matches(TELEPHONE_VALIDATOR);
    }
}

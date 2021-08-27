package by.epam.onlinepharmacy.validation;

public class UserValidator {
    private static final String EMAIL_VALIDATOR = "^[^.][\\w.!#$%&’*+/=?^_`{|}~-]+@\\w+?\\.\\w+$";
    private static final String TELEPHONE_VALIDATOR = "^\\+[0-9]{12}$";
    private static final int MAX_SYMBOLS_DATA_USER = 45;

    public static boolean isValidLogin(String login) {
        return !login.isBlank() && login.length() <= MAX_SYMBOLS_DATA_USER;
    }

    public static boolean isValidPassword(String password) {
        return !password.isBlank() && password.length() <= MAX_SYMBOLS_DATA_USER;
    }

    public static boolean isValidFirstName(String firstName) {
        return !firstName.isBlank() && firstName.length() <= MAX_SYMBOLS_DATA_USER;
    }

    public static boolean isValidLastName(String lastName) {
        return !lastName.isBlank() && lastName.length() <= MAX_SYMBOLS_DATA_USER;
    }

    public static boolean isValidEmailRegistrationUser(String email) {
        return !email.isBlank() && email.length() <= MAX_SYMBOLS_DATA_USER && email.matches(EMAIL_VALIDATOR);
    }

    public static boolean isValidTelephoneRegistrationUser(String telephone) {
        return !telephone.isBlank() && telephone.length() <= MAX_SYMBOLS_DATA_USER && telephone.matches(TELEPHONE_VALIDATOR);
    }
}

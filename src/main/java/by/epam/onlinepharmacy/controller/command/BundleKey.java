package by.epam.onlinepharmacy.controller.command;

public final class BundleKey {
    public static final String REGISTRATION_ERROR = "error.registration";
    public static final String LOGIN_ERROR = "Incorrect login or password";
    public static final String CODE_VERIFICATION_ERROR = "Incorrect verification code";
    public static final String DATA_REGISTRATION_ERROR = "Some fields are incorrect";
    public static final String USER_LOGIN_ERROR = "Login length must be between 1 and 45 symbols";
    public static final String FIRST_NAME_ERROR = "First name must be between 1 and 45 symbols";
    public static final String LAST_NAME_ERROR = "Last name must be between 1 and 45 symbols";
    public static final String INCORRECT_EMAIL = "Incorrect email";
    public static final String INCORRECT_TELEPHONE = "Incorrect telephone";
    public static final String PHARMACY_NUMBER_ERROR = "Incorrect number";
    public static final String PHARMACY_STRING_PARAMETERS_ERROR = "Incorrect city or street or house";
    public static final String PHARMACY_BLOCK_ERROR = "Incorrect block";

    private BundleKey() {
    }
}

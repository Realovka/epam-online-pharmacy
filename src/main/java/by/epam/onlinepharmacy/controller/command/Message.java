package by.epam.onlinepharmacy.controller.command;

public final class Message {
    public static final String REGISTRATION_ERROR = "Such login already exists";
    public static final String LOGIN_ERROR = "Incorrect login or password";
    public static final String CODE_VERIFICATION_ERROR = "Incorrect verification code";
    public static final String USER_DATA_REGISTRATION_ERROR = "Number of symbols must be between 1 and 45";
    public static final String INCORRECT_EMAIL = "Incorrect email";
    public static final String INCORRECT_TELEPHONE = "Incorrect telephone";
    public static final String PHARMACY_NUMBER_ERROR = "Incorrect number";
    public static final String PHARMACY_STRING_PARAMETERS_ERROR = "Incorrect city or street or house";
    public static final String PHARMACY_BLOCK_ERROR = "Incorrect block";

    private Message() {
    }
}

package by.epam.onlinepharmacy.controller.command;

public final class SessionAttribute {
    public static final String USER_AUTH = "authUser";
    public static final String LOGIN_ERROR = "loginError";
    public static final String CODE_VERIFICATION_ERROR = "codeVerificationError";
    public static final String ALL_PHARMACISTS = "allPharmacists";
    public static final String INACTIVE_PHARMACISTS = "inactivePharmacists";
    public static final String ALL_PHARMACIES = "allPharmacies";
    public static final String PHARMACIST_ID = "id";
    public static final String PREVIOUS_QUERY = "previousQuery";
    public static final String CURRENT_LOCALE = "currentLocale";
    public static final String SECOND_LOCALE = "secondLocale";
    public static final String CURRENT_BUNDLE = "currentBundle";


    private SessionAttribute() {
    }
}

package by.epam.onlinepharmacy.controller.command;

public final class SessionAttribute {
    public static final String USER_AUTH = "authUser";
    public static final String REGISTRATION_ERROR = "registrationError";
    public static final String LOGIN_ERROR = "loginError";
    public static final String CODE_VERIFICATION_ERROR = "codeVerificationError";
    public static final String ALL_PHARMACISTS = "allPharmacists";
    public static final String INACTIVE_PHARMACISTS = "inactivePharmacists";

    private SessionAttribute() {
    }
}

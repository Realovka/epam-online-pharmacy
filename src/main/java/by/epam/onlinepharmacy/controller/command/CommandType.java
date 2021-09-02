package by.epam.onlinepharmacy.controller.command;

public enum CommandType {
    DEFAULT,
    LOGIN,
    LOGOUT,
    REGISTRATION,
    LOGIN_PAGE,
    VERIFICATION_CUSTOMER,
    REGISTRATION_PAGE,
    VERIFICATION_CUSTOMER_PAGE,
    ALL_PHARMACISTS,
    MAIN_ADMIN,
    VERIFICATION_PHARMACIST,
    INACTIVATION_PHARMACIST,
    INACTIVE_PHARMACISTS_PAGE,
    ACTIVATION_PHARMACIST,
    ALL_PHARMACIES,
    ADDITION_PHARMACY,
    UPDATING_PHARMACIST_LOGIN_PAGE,
    UPDATING_PHARMACIST_FIRST_NAME_PAGE,
    UPDATING_PHARMACIST_LAST_NAME_PAGE,
    UPDATING_PHARMACIST_EMAIL_PAGE,
    UPDATING_PHARMACIST_TELEPHONE_PAGE,
    UPDATING_PHARMACIST_LOGIN,
    UPDATING_PHARMACIST_FIRST_NAME,
    UPDATING_PHARMACIST_LAST_NAME,
    UPDATING_PHARMACIST_EMAIL,
    UPDATING_PHARMACIST_TELEPHONE,
    ALL_PRODUCTS,
    CHANGE_LANGUAGE,

    //pharmacist
    MAIN_PHARMACIST,

    //customer
    MAIN_CUSTOMER;

    public static CommandType convertRequestParameterToCommandType(String parameter) {
        if (parameter == null) {
            return DEFAULT;
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(parameter.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType;
    }
}

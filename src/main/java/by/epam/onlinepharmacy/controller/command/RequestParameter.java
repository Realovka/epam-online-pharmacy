package by.epam.onlinepharmacy.controller.command;

public final class RequestParameter {
    public static final String COMMAND = "command";

    //User
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String TELEPHONE = "telephone";
    public static final String ROLE = "role";
    public static final String CODE = "code";
    public static final String USER_ID = "id";

    //Pharmacist update
    public static final String UPDATING_PHARMACIST_LOGIN = "newLogin";
    public static final String UPDATING_PHARMACIST_FIRSTNAME = "newFirstName";

    //Pharmacy
    public static final String NUMBER = "number";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String HOUSE = "house";
    public static final String BLOCK = "block";



    private RequestParameter() {
    }
}

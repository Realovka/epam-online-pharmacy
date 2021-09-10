package by.epam.onlinepharmacy.controller.command;

public final class RequestParameter {
    public static final String COMMAND = "command";
    public static final String LANGUAGE = "lang";
    public static final String CURRENT_URL = "current_url";

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
    public static final String UPDATING_PHARMACIST_FIRST_NAME = "newFirstName";
    public static final String UPDATING_PHARMACIST_LAST_NAME = "newLastName";
    public static final String UPDATING_PHARMACIST_EMAIL = "newEmail";
    public static final String UPDATING_PHARMACIST_TELEPHONE = "newTelephone";

    //Pharmacy
    public static final String PHARMACY_ID = "pharmacyId";
    public static final String NUMBER = "number";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String HOUSE = "house";
    public static final String BLOCK = "block";

    //Product
    public static final String PRODUCT_ID = "productId";
    public static final String NAME = "name";
    public static final String GROUP = "group";
    public static final String PRICE = "price";
    public static final String RECIPE = "recipe";
    public static final String INSTRUCTION = "instruction";
    public static final String QUANTITY = "quantity";


    private RequestParameter() {
    }
}

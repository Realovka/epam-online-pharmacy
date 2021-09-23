package by.epam.onlinepharmacy.model.dao;

public final class ColumnName {
    //table users
    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_TELEPHONE = "telephone";
    public static final String USER_STATUS_ID = "status_id";
    public static final String USER_ROLE_ID = "role_id";

    //table user_status
    public static final String USER_STATUS = "status";

    //table user_role
    public static final String USER_ROLE = "role";

    //table pharmacies
    public static final String PHARMACY_ID = "pharmacy_id";
    public static final String PHARMACY_NUMBER = "number";
    public static final String PHARMACY_CITY = "city";
    public static final String PHARMACY_STREET = "street";
    public static final String PHARMACY_HOUSE = "house";
    public static final String PHARMACY_BLOCK = "block";

    //table products
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_NON_PROPRIETARY_NAME = "inpn";
    public static final String PRODUCT_DOSE = "product_dose";
    public static final String PRODUCT_PLANT = "plant";
    public static final String PRODUCT_GROUP = "product_group";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_RECIPE = "recipe";
    public static final String PRODUCT_PICTURE = "picture";
    public static final String PRODUCT_INSTRUCTION = "instruction";

    //table orders
    public static final String ORDER_ID = "order_id";

    private ColumnName() {
    }
}

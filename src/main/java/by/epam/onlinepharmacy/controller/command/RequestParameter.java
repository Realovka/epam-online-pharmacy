package by.epam.onlinepharmacy.controller.command;

public final class RequestParameter {
    public static final String COMMAND = "command";
    public static final String LANGUAGE = "lang";
    public static final String CURRENT_URL = "current_url";
    public static final String CURRENT_PAGE = "current_page";
    public static final String COUNT_FORWARD = "count_forward";
    public static final String COUNT_BACK = "count_back";

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
    public static final String PHARMACY_ID = "pharmacy_id";
    public static final String NUMBER = "number";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String HOUSE = "house";
    public static final String BLOCK = "block";
    public static final String UPDATING_PHARMACY_NUMBER = "newNumber";
    public static final String UPDATING_PHARMACY_CITY = "newCity";
    public static final String UPDATING_PHARMACY_STREET = "newStreet";
    public static final String UPDATING_PHARMACY_HOUSE = "newHouse";
    public static final String UPDATING_PHARMACY_BLOCK = "newBlock";

    //Product
    public static final String PRODUCT_ID = "product_id";
    public static final String NAME = "name";
    public static final String NON_PROPRIETARY_NAME = "nonProprietaryName";
    public static final String DOSE = "dose";
    public static final String GROUP = "group";
    public static final String PLANT = "plant";
    public static final String PRICE = "price";
    public static final String RECIPE = "recipe";
    public static final String INSTRUCTION = "instruction";
    public static final String QUANTITY = "quantity";
    public static final String UPDATING_PRODUCT_NAME = "newName";
    public static final String UPDATING_PRODUCT_DOSE = "newDose";
    public static final String UPDATING_PRODUCT_NON_PROPRIETARY_NAME = "newNonProprietaryName";
    public static final String UPDATING_PRODUCT_PLANT = "newPlant";
    public static final String UPDATING_PRODUCT_GROUP = "newGroup";
    public static final String UPDATING_PRODUCT_PRICE = "newPrice";
    public static final String UPDATING_PRODUCT_RECIPE = "newRecipe";
    public static final String UPDATING_PRODUCT_INSTRUCTION = "newInstruction";
    public static final String NAME_FOR_SEARCH_PRODUCTS = "nameForSearchProducts";
    public static final String NON_PROPRIETARY_NAME_FOR_SEARCH_PRODUCTS = "nonProprietaryNameForSearchProducts";
    public static final String CITY_FOR_SEARCH_PHARMACIES = "cityForSearchPharmacies";

    //Order
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_STATUS_ID = "order_status_id";
    public static final String OLD_ORDER_STATUS_ID = "old_order_status_id";




    private RequestParameter() {
    }
}

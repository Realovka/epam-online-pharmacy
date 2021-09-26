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

    //admin
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
    ADDITION_PRODUCT,
    ADDITION_PICTURE_PAGE,
    SEE_PRODUCT,
    UPDATING_PHARMACY_NUMBER_PAGE,
    UPDATING_PHARMACY_CITY_PAGE,
    UPDATING_PHARMACY_STREET_PAGE,
    UPDATING_PHARMACY_HOUSE_PAGE,
    UPDATING_PHARMACY_BLOCK_PAGE,
    UPDATING_PHARMACY_NUMBER,
    UPDATING_PHARMACY_CITY,
    UPDATING_PHARMACY_STREET,
    UPDATING_PHARMACY_HOUSE,
    UPDATING_PHARMACY_BLOCK,
    UPDATING_PRODUCT_NAME_PAGE,
    UPDATING_PRODUCT_NON_PROPRIETARY_NAME_PAGE,
    UPDATING_PRODUCT_DOSE_PAGE,
    UPDATING_PRODUCT_PLANT_PAGE,
    UPDATING_PRODUCT_GROUP_PAGE,
    UPDATING_PRODUCT_RECIPE_PAGE,
    UPDATING_PRODUCT_PRICE_PAGE,
    UPDATING_PRODUCT_INSTRUCTION_PAGE,
    UPDATING_PRODUCT_NAME,
    UPDATING_PRODUCT_NON_PROPRIETARY_NAME,
    UPDATING_PRODUCT_DOSE,
    UPDATING_PRODUCT_PLANT,
    UPDATING_PRODUCT_GROUP,
    UPDATING_PRODUCT_RECIPE,
    UPDATING_PRODUCT_PRICE,
    UPDATING_PRODUCT_INSTRUCTION,


    //pharmacist
    MAIN_PHARMACIST,

    //customer
    MAIN_CUSTOMER,
    SEARCH_PRODUCTS_BY_NAME_PAGE,
    SEARCH_PRODUCTS_BY_NON_PROPRIETARY_NAME_PAGE,
    SEARCH_PRODUCTS_BY_NAME,
    SEARCH_PRODUCTS_BY_NON_PROPRIETARY_NAME,
    SEARCH_PHARMACIES_BY_CITY,
    ABOUT_PRODUCT,
    ADDITION_PRODUCT_TO_ORDER,
    BASKET_PAGE,
    UPDATING_PRODUCT_QUANTITY,
    CHOOSE_PHARMACY,
    ORDER,
    SEND_ORDER

}

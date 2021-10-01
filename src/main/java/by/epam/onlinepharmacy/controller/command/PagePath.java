package by.epam.onlinepharmacy.controller.command;

public final class PagePath {
    //common
    public static final String LOGIN = "/pages/login.jsp";
    public static final String REGISTRATION = "/pages/registration.jsp";
    public static final String VERIFICATION_CUSTOMER = "/pages/verification_customer.jsp";

    //admin
    public static final String MAIN_ADMIN = "/pages/admin/admin_main.jsp";
    public static final String ALL_PHARMACISTS = "/pages/admin/all_pharmacists.jsp";
    public static final String INACTIVE_PHARMACISTS = "/pages/admin/inactive_pharmacists.jsp";
    public static final String ALL_PHARMACIES = "/pages/admin/all_pharmacies.jsp";
    public static final String ALL_PRODUCTS = "/pages/admin/all_products.jsp";
    public static final String UPDATING_PHARMACIST_LOGIN = "/pages/admin/updating_pharmacist_login.jsp";
    public static final String UPDATING_PHARMACIST_FIRST_NAME = "/pages/admin/updating_pharmacist_first_name.jsp";
    public static final String UPDATING_PHARMACIST_LAST_NAME = "/pages/admin/updating_pharmacist_last_name.jsp";
    public static final String UPDATING_PHARMACIST_EMAIL = "/pages/admin/updating_pharmacist_email.jsp";
    public static final String UPDATING_PHARMACIST_TELEPHONE = "/pages/admin/updating_pharmacist_telephone.jsp";
    public static final String UPDATING_PHARMACY_NUMBER = "/pages/admin/updating_pharmacy_number.jsp";
    public static final String UPDATING_PHARMACY_CITY = "/pages/admin/updating_pharmacy_city.jsp";
    public static final String UPDATING_PHARMACY_STREET = "/pages/admin/updating_pharmacy_street.jsp";
    public static final String UPDATING_PHARMACY_HOUSE = "/pages/admin/updating_pharmacy_house.jsp";
    public static final String UPDATING_PHARMACY_BLOCK = "/pages/admin/updating_pharmacy_block.jsp";
    public static final String ADDITION_PICTURE = "/pages/admin/addition_picture.jsp";
    public static final String SEE_PRODUCT = "/pages/admin/about_product.jsp";
    public static final String UPDATING_PRODUCT_NAME = "/pages/admin/updating_product_name.jsp";
    public static final String UPDATING_PRODUCT_GROUP = "/pages/admin/updating_product_group.jsp";
    public static final String UPDATING_PRODUCT_PRICE = "/pages/admin/updating_product_price.jsp";
    public static final String UPDATING_PRODUCT_RECIPE = "/pages/admin/updating_product_recipe.jsp";
    public static final String UPDATING_PRODUCT_INSTRUCTION = "/pages/admin/updating_product_instruction.jsp";
    public static final String UPDATING_PRODUCT_NON_PROPRIETARY_NAME = "/pages/admin/updating_product_non_proprietary_name.jsp";
    public static final String UPDATING_PRODUCT_DOSE = "/pages/admin/updating_product_dose.jsp";
    public static final String UPDATING_PRODUCT_PLANT = "/pages/admin/updating_product_plant.jsp";

    //customer
    public static final String MAIN_CUSTOMER = "/pages/customer/customer_main.jsp";
    public static final String SEARCH_PRODUCTS_BY_NAME = "/pages/customer/products_by_name.jsp";
    public static final String SEARCH_PRODUCTS_BY_NON_PROPRIETARY_NAME = "/pages/customer/products_by_non_proprietary_name.jsp";
    public static final String ABOUT_PRODUCT = "/pages/customer/about_product.jsp";
    public static final String BASKET = "/pages/customer/basket.jsp";
    public static final String PHARMACIES_FOR_CUSTOMER = "/pages/customer/pharmacies_for_customer.jsp";
    public static final String ORDER = "/pages/customer/order.jsp";
    public static final String ORDER_ACCEPT = "/pages/customer/order_accept.jsp";

    //pharmacist
    public static final String MAIN_PHARMACIST = "/pages/pharmacist/pharmacist_main.jsp";
    public static final String ALL_PROCESSING_ORDERS = "/pages/pharmacist/all_processing_orders.jsp";
    public static final String BASKET_FOR_ORDER = "/pages/pharmacist/basket_for_order.jsp";

    //error
    public static final String ERROR_500_PAGE = "/pages/error/error500.jsp";
    public static final String ERROR_404_PAGE = "/pages/error/error404.jsp";

    private PagePath() {
    }
}

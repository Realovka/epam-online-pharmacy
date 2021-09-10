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
    public static final String ADDITION_PICTURE = "/pages/admin/addition_picture.jsp";
    public static final String VIEW_PICTURE = "/pages/admin/view_picture.jsp";

    //customer
    public static final String MAIN_CUSTOMER = "/pages/customer/customer_main.jsp";
    public static final String PRODUCTS_FOR_CUSTOMER = "/pages/customer/products_for_customer.jsp";
    public static final String ABOUT_PRODUCT = "/pages/customer/about_product.jsp";
    public static final String BASKET = "/pages/customer/basket.jsp";

    //pharmacist
    public static final String MAIN_PHARMACIST = "/pages/pharmacist/pharmacist_main.jsp";

    //error
    public static final String ERROR_500_PAGE = "/pages/error/error500.jsp";
    public static final String ERROR_404_PAGE = "/pages/error/error404.jsp";

    private PagePath() {
    }
}

package by.epam.onlinepharmacy.controller.command;

public final class PagePath {
    public static final String MAIN_PHARMACIST = "";
    public static final String MAIN_CUSTOMER = "";
    public static final String MAIN_ADMIN = "pages/admin/admin_main.jsp";
    public static final String LOGIN = "login.jsp";
    public static final String REGISTRATION = "registration.jsp";
    public static final String VERIFICATION_CUSTOMER = "verification_customer.jsp";
    public static final String ALL_PHARMACISTS = "pages/admin/all_pharmacists.jsp";
    public static final String INACTIVE_PHARMACISTS = "pages/admin/inactive_pharmacists.jsp";
    public static final String ALL_PHARMACIES = "pages/admin/all_pharmacies.jsp";
    public static final String ALL_PRODUCTS = "pages/admin/all_products.jsp";
    public static final String UPDATING_PHARMACIST_LOGIN = "pages/admin/updating_pharmacist_login.jsp";
    public static final String UPDATING_PHARMACIST_FIRST_NAME = "pages/admin/updating_pharmacist_first_name.jsp";

    public static final String ERROR_500_PAGE = "pages/error/error500.jsp";

    private PagePath() {
    }
}

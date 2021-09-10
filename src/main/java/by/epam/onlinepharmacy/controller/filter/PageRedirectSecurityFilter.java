package by.epam.onlinepharmacy.controller.filter;

import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import static by.epam.onlinepharmacy.controller.command.PagePath.*;

@WebFilter(urlPatterns = {"/pages/*"})
public class PageRedirectSecurityFilter implements Filter {
    private Logger logger = LogManager.getLogger();
    private EnumMap<Role, List<String>> availablePages;
    private List<String> adminPages;
    private List<String> customerPages;
    private List<String> pharmacistPages;
    private List<String> guestPages;

    @Override
    public void init(FilterConfig filterConfig) {
        availablePages = new EnumMap<>(Role.class);
        guestPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE, LOGIN, REGISTRATION, VERIFICATION_CUSTOMER);

        adminPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE, MAIN_ADMIN, ALL_PHARMACISTS, INACTIVE_PHARMACISTS,
                ALL_PHARMACIES, ALL_PRODUCTS,UPDATING_PHARMACIST_LOGIN, UPDATING_PHARMACIST_FIRST_NAME,
                UPDATING_PHARMACIST_LAST_NAME, UPDATING_PHARMACIST_EMAIL, UPDATING_PHARMACIST_TELEPHONE,
                ADDITION_PICTURE, VIEW_PICTURE);

        customerPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE, MAIN_CUSTOMER, PRODUCTS_FOR_CUSTOMER, ABOUT_PRODUCT,
                BASKET);

        pharmacistPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE, MAIN_PHARMACIST);

        availablePages.put(Role.GUEST, guestPages);
        availablePages.put(Role.ADMIN, adminPages);
        availablePages.put(Role.PHARMACIST, pharmacistPages);
        availablePages.put(Role.CUSTOMER, customerPages);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String servletPath = httpServletRequest.getServletPath();

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER_AUTH);
        Role role;

        if (user == null) {
            role = Role.GUEST;
        } else {
            role = user.getRole();
        }

        if (role.equals(Role.ADMIN) && !adminPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }

        if (role.equals(Role.CUSTOMER) && !customerPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }

        if (role.equals(Role.PHARMACIST) && !pharmacistPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }

        if (role.equals(Role.GUEST) && !guestPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }
        chain.doFilter(request, response);
    }
}

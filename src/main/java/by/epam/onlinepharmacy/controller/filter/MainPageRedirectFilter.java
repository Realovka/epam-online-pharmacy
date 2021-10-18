package by.epam.onlinepharmacy.controller.filter;

import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Main page redirect filter.
 */
@WebFilter(urlPatterns = {"/pages/login.jsp"})
public class MainPageRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User authUser = (User) session.getAttribute(SessionAttribute.USER_AUTH);
        if (!session.isNew() && authUser != null) {
            forwardToMainPage(authUser, request, response);
        } else {
            request.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }
        chain.doFilter(request, response);
    }

    private void forwardToMainPage(User authUser, ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Role role = authUser.getRole();
        switch (role) {
            case ADMIN -> request.getRequestDispatcher(PagePath.MAIN_ADMIN).forward(request, response);
            case PHARMACIST -> request.getRequestDispatcher(PagePath.MAIN_PHARMACIST).forward(request, response);
            case CUSTOMER -> request.getRequestDispatcher(PagePath.MAIN_CUSTOMER).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

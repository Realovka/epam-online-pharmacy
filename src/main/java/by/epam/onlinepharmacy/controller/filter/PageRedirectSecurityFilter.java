package by.epam.onlinepharmacy.controller.filter;

import by.epam.onlinepharmacy.controller.command.CommandType;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static by.epam.onlinepharmacy.controller.command.CommandType.*;


public class PageRedirectSecurityFilter implements Filter {
    private Logger logger = LogManager.getLogger();
    private EnumMap<Role, List<String>> availableCommands;
    private List<String> adminCommands;
    private EnumSet<CommandType> customerCommands;
    private List<String> pharmacistCommands;
    private EnumSet<CommandType> guestCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        availableCommands = new EnumMap<>(Role.class);
        guestCommands = EnumSet.of(DEFAULT, LOGIN,LOGIN_PAGE, VERIFICATION_CUSTOMER,
                REGISTRATION, REGISTRATION_PAGE, VERIFICATION_CUSTOMER_PAGE, VERIFICATION_PHARMACIST, CHANGE_LANGUAGE);

        adminCommands = List.of(PagePath.MAIN_ADMIN);

        customerCommands = EnumSet.of(DEFAULT, LOGIN, LOGOUT, CHANGE_LANGUAGE, MAIN_CUSTOMER);

        pharmacistCommands = List.of(PagePath.MAIN_PHARMACIST);

//        availableCommands.put(Role.GUEST, guestCommands);
        availableCommands.put(Role.ADMIN, adminCommands);
        availableCommands.put(Role.PHARMACIST, pharmacistCommands);
//        availableCommands.put(Role.CUSTOMER, customerCommands);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String commandString = httpServletRequest.getParameter(RequestParameter.COMMAND);


        String servletPath = httpServletRequest.getServletPath();


        CommandType currentCommand = CommandType.convertRequestParameterToCommandType(commandString);

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER_AUTH);
        Role role;

        if (user == null) {
            role = Role.GUEST;
        } else {
            role = user.getRole();
        }

//        EnumSet<CommandType> availableCommandsForCurrentUser = availableCommands.get(role);

        if (role.equals(Role.ADMIN) && !adminCommands.contains(servletPath)) {
            String s = httpServletRequest.getContextPath()+PagePath.LOGIN;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+PagePath.LOGIN);
        }

        if (role.equals(Role.CUSTOMER) && !customerCommands.contains(currentCommand)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PagePath.LOGIN);
            return;
        }

        if (role.equals(Role.PHARMACIST) && !pharmacistCommands.contains(servletPath)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PagePath.LOGIN);
            return;
        }

        if (role.equals(Role.GUEST) && !guestCommands.contains(currentCommand)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PagePath.LOGIN);
            return;
        }
        chain.doFilter(request, response);
    }
}

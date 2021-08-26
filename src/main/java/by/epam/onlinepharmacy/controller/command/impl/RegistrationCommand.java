package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegistrationCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String telephone = request.getParameter(RequestParameter.TELEPHONE);
        String role = request.getParameter(RequestParameter.ROLE);
        try {
            UserService userService = UserServiceImpl.getInstance();
            Optional<User> user = userService.createUser(login, password, firstName, lastName, email, telephone, role);
            if (user.isPresent()) {
                if (user.get().getRole().equals(Role.CUSTOMER)) {
                    return new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
                } else {
                    return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
                }
            } else {
                session.setAttribute(SessionAttribute.REGISTRATION_ERROR, Message.REGISTRATION_ERROR);
                return new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}

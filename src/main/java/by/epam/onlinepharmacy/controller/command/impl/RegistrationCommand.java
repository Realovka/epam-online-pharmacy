package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import by.epam.onlinepharmacy.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegistrationCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult commandResult;
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String telephone = request.getParameter(RequestParameter.TELEPHONE);
        String role = request.getParameter(RequestParameter.ROLE);

        if (!UserValidator.isValidAllParametersRegistrationUser(login, password, firstName, lastName, email, telephone)) {
            request.setAttribute(RequestAttribute.USER_REGISTRATION_DATA_ERROR, Message.USER_DATA_REGISTRATION_ERROR);
        }

        if (!UserValidator.isValidEmailRegistrationUser(email)) {
            request.setAttribute(RequestAttribute.EMAIL_ERROR, Message.INCORRECT_EMAIL);
        }

        if(!UserValidator.isValidTelephoneRegistrationUser(telephone)) {
            request.setAttribute(RequestAttribute.TELEPHONE_ERROR, Message.INCORRECT_TELEPHONE);
        }

        if(request.getAttributeNames().hasMoreElements()) {
            return new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.FORWARD);
        }
        try {
            UserService userService = UserServiceImpl.getInstance();
            Optional<User> user = userService.createUser(login, password, firstName, lastName, email, telephone, role);
            if (user.isPresent()) {
                if (user.get().getRole().equals(Role.CUSTOMER)) {
                    commandResult = new  CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
                } else {
                    commandResult = new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.FORWARD);
                }
            } else {
                request.setAttribute(RequestAttribute.REGISTRATION_ERROR, Message.REGISTRATION_ERROR);
                commandResult = new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            commandResult = new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}
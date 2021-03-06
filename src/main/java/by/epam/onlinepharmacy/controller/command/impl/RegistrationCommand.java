package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.BundleKey;
import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestAttribute;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * The type Registration command.
 */
public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMPTY_STRING = "\s";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult commandResult;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String telephone = request.getParameter(RequestParameter.TELEPHONE);
        String role = request.getParameter(RequestParameter.ROLE);

        UserService userService = UserServiceImpl.getInstance();
        Map<String, String> mapData = userService.isFormValid(login, password, firstName, lastName, email, telephone);

        request.setAttribute(RequestAttribute.MAP_DATA, mapData);
        if (mapData.containsValue(EMPTY_STRING)) {
            request.setAttribute(RequestAttribute.DATA_REGISTRATION_ERROR, BundleKey.DATA_REGISTRATION_ERROR);
            return new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.FORWARD);
        }

        try {
            Optional<User> user = userService.createUser(login, password, firstName, lastName, email, telephone, role);
            if (user.isPresent()) {
                if (user.get().getRole().equals(Role.CUSTOMER)) {
                    commandResult = new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
                } else {
                    commandResult = new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.FORWARD);
                }
            } else {
                mapData.put(RequestParameter.LOGIN, EMPTY_STRING);
                request.setAttribute(RequestAttribute.REGISTRATION_ERROR, BundleKey.REGISTRATION_ERROR);
                commandResult = new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute while create user ", e);
            commandResult = new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}

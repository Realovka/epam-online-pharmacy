package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.BundleKey;
import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestAttribute;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import by.epam.onlinepharmacy.model.validation.UserValidator;
import by.epam.onlinepharmacy.model.validation.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Updating pharmacist login command.
 */
public class UpdatingPharmacistLoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(SessionAttribute.PHARMACIST_ID);
        String newLogin = request.getParameter(RequestParameter.UPDATING_PHARMACIST_LOGIN);
        UserService userService = UserServiceImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> pharmacists;

        if (!userValidator.isValidStringParameter(newLogin)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACIST_LOGIN_ERROR, BundleKey.USER_LOGIN_ERROR);
            return new CommandResult(PagePath.UPDATING_PHARMACIST_LOGIN, CommandResult.RoutingType.FORWARD);
        }
        try {
            if (userService.updateLogin(id, newLogin)) {
                pharmacists = userService.findAllPharmacists();
                session.setAttribute(SessionAttribute.ALL_PHARMACISTS, pharmacists);
                return new CommandResult(PagePath.ALL_PHARMACISTS, CommandResult.RoutingType.REDIRECT);
            } else {
                request.setAttribute(RequestAttribute.REGISTRATION_ERROR, BundleKey.REGISTRATION_ERROR);
                return new CommandResult(PagePath.UPDATING_PHARMACIST_LOGIN, CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find all pharmacists ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}

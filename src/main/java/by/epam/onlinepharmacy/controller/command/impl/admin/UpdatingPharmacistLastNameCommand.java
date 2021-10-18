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
 * The type Updating pharmacist last name command.
 */
public class UpdatingPharmacistLastNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(SessionAttribute.PHARMACIST_ID);
        String newLastName = request.getParameter(RequestParameter.UPDATING_PHARMACIST_LAST_NAME);
        UserService userService = UserServiceImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> pharmacists;

        if (!userValidator.isValidStringParameter(newLastName)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACIST_LAST_NAME_ERROR, BundleKey.LAST_NAME_ERROR);
            return new CommandResult(PagePath.UPDATING_PHARMACIST_LAST_NAME, CommandResult.RoutingType.FORWARD);
        }
        try {
            userService.updateLastName(id, newLastName);
            pharmacists = userService.findAllPharmacists();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while update last name or find all pharmacists ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PHARMACISTS, pharmacists);
        return new CommandResult(PagePath.ALL_PHARMACISTS, CommandResult.RoutingType.REDIRECT);
    }
}

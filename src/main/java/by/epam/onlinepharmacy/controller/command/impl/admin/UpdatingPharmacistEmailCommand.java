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

public class UpdatingPharmacistEmailCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(SessionAttribute.PHARMACIST_ID);
        String newEmail = request.getParameter(RequestParameter.UPDATING_PHARMACIST_EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> pharmacists;

        if (!userValidator.isValidEmailRegistrationUser(newEmail)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACIST_EMAIL_ERROR, BundleKey.INCORRECT_EMAIL);
            return new CommandResult(PagePath.UPDATING_PHARMACIST_EMAIL, CommandResult.RoutingType.FORWARD);
        }
        try {
            userService.updateEmail(id, newEmail);
            pharmacists = userService.findAllPharmacists();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while send email or find all pharmacists", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PHARMACISTS, pharmacists);
        return new CommandResult(PagePath.ALL_PHARMACISTS, CommandResult.RoutingType.REDIRECT);
    }
}

package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import by.epam.onlinepharmacy.validation.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdatingPharmacistTelephoneCommand implements Command {
    private Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(SessionAttribute.PHARMACIST_ID);
        String newTelephone = request.getParameter(RequestParameter.UPDATING_PHARMACIST_TELEPHONE);
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> pharmacists;

        if (!UserValidatorImpl.getInstance().isValidTelephoneRegistrationUser(newTelephone)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACIST_TELEPHONE_ERROR, BundleKey.INCORRECT_TELEPHONE);
            return new CommandResult(PagePath.UPDATING_PHARMACIST_TELEPHONE, CommandResult.RoutingType.FORWARD);
        }
        try {
            userService.updateTelephone(id, newTelephone);
            pharmacists = userService.findAllPharmacists();
            session.setAttribute(SessionAttribute.ALL_PHARMACISTS, pharmacists);
            return new CommandResult(PagePath.ALL_PHARMACISTS, CommandResult.RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}

package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Go to inactive pharmacists page command.
 */
public class GoToInactivePharmacistsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        List<User> inactivePharmacists;
        try {
            inactivePharmacists = userService.findInactivePharmacists();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find inactive pharmacists ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.INACTIVE_PHARMACISTS, inactivePharmacists);
        return new CommandResult(PagePath.INACTIVE_PHARMACISTS, CommandResult.RoutingType.REDIRECT);
    }
}

package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.UserService;
import by.epam.onlinepharmacy.model.service.impl.UserServiceImpl;
import by.epam.onlinepharmacy.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdatingPharmacistLastNameCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(SessionAttribute.PHARMACIST_ID);
        String newLastName = request.getParameter(RequestParameter.UPDATING_PHARMACIST_LAST_NAME);
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> pharmacists;

        if (!UserValidator.isValidStringParameter(newLastName)) {
            request.setAttribute(RequestAttribute.UPDATING_PHARMACIST_LAST_NAME_ERROR, BundleKey.LAST_NAME_ERROR);
            return new CommandResult(PagePath.UPDATING_PHARMACIST_LAST_NAME, CommandResult.RoutingType.FORWARD);
        }
        try {
            userService.updateLastName(id, newLastName);
            pharmacists = userService.findAllPharmacists();
            session.setAttribute(SessionAttribute.ALL_PHARMACISTS, pharmacists);
            return new CommandResult(PagePath.ALL_PHARMACISTS, CommandResult.RoutingType.REDIRECT);
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}

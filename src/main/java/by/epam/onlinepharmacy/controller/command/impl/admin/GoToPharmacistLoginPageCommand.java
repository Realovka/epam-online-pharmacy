package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to pharmacist login page command.
 */
public class GoToPharmacistLoginPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.USER_ID);
        long pharmacistId = Long.parseLong(id);
        request.getSession().setAttribute(SessionAttribute.PHARMACIST_ID, pharmacistId);
        return new CommandResult(PagePath.UPDATING_PHARMACIST_LOGIN, CommandResult.RoutingType.REDIRECT);
    }
}

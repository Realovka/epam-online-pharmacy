package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to updating pharmacy block page command.
 */
public class GoToUpdatingPharmacyBlockPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.PHARMACY_ID);
        long pharmacyId = Long.parseLong(id);
        request.getSession().setAttribute(SessionAttribute.PHARMACY_ID, pharmacyId);
        return new CommandResult(PagePath.UPDATING_PHARMACY_BLOCK, CommandResult.RoutingType.REDIRECT);
    }
}

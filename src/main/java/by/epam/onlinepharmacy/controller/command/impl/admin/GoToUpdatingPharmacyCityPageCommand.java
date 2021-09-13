package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;

import javax.servlet.http.HttpServletRequest;

public class GoToUpdatingPharmacyCityPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.PHARMACY_ID);
        request.getSession().setAttribute(SessionAttribute.PHARMACY_ID, Long.parseLong(id));
        return new CommandResult(PagePath.UPDATING_PHARMACY_CITY, CommandResult.RoutingType.REDIRECT);
    }
}

package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;

import javax.servlet.http.HttpServletRequest;

public class GoToUpdatingPharmacyStreetPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.PHARMACY_ID);
        long pharmacyId = Long.parseLong(id);
        request.getSession().setAttribute(SessionAttribute.PHARMACY_ID, pharmacyId);
        return new CommandResult(PagePath.UPDATING_PHARMACY_STREET, CommandResult.RoutingType.REDIRECT);
    }
}

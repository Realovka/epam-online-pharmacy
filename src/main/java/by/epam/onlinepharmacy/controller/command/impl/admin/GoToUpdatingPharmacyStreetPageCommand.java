package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GoToUpdatingPharmacyStreetPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        String id = request.getParameter(RequestParameter.PHARMACY_ID);
        request.getSession().setAttribute(SessionAttribute.PHARMACY_ID, Long.parseLong(id));
        return new CommandResult(PagePath.UPDATING_PHARMACY_STREET, CommandResult.RoutingType.REDIRECT);
    }
}

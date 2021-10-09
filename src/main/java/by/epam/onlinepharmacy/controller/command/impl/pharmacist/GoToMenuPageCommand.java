package by.epam.onlinepharmacy.controller.command.impl.pharmacist;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToMenuPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pharmacyId = request.getParameter(RequestParameter.PHARMACY_ID);
        long id = Long.parseLong(pharmacyId);
        session.setAttribute(SessionAttribute.PHARMACY_ID, id);
        return new CommandResult(PagePath.MENU, CommandResult.RoutingType.REDIRECT);
    }
}

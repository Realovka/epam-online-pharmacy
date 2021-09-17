package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;

import javax.servlet.http.HttpServletRequest;

public class GoToUpdatingPharmacistLastNamePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.USER_ID);
        long pharmacistId = Long.parseLong(id);
        request.getSession().setAttribute(SessionAttribute.PHARMACIST_ID, pharmacistId);
        return new CommandResult(PagePath.UPDATING_PHARMACIST_LAST_NAME, CommandResult.RoutingType.REDIRECT);
    }
}
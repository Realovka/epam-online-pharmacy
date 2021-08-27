package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.*;

import javax.servlet.http.HttpServletRequest;

public class UpdatingLoginPharmacistPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.USER_ID);
        request.getSession().setAttribute(SessionAttribute.PHARMACIST_ID, Long.parseLong(id));
        return new CommandResult(PagePath.UPDATING_PHARMACIST_LOGIN, CommandResult.RoutingType.REDIRECT);
    }
}

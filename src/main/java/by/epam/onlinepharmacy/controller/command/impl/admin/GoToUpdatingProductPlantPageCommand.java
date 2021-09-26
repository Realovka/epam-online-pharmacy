package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;

import javax.servlet.http.HttpServletRequest;

public class GoToUpdatingProductPlantPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        long id = Long.parseLong(productId);
        request.getSession().setAttribute(SessionAttribute.PRODUCT_ID, id);
        return new CommandResult(PagePath.UPDATING_PRODUCT_PLANT, CommandResult.RoutingType.REDIRECT);
    }
}

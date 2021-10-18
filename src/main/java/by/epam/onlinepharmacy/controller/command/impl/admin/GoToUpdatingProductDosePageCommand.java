package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to updating product dose page command.
 */
public class GoToUpdatingProductDosePageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        long id = Long.parseLong(productId);
        request.getSession().setAttribute(SessionAttribute.PRODUCT_ID, id);
        return new CommandResult(PagePath.UPDATING_PRODUCT_DOSE, CommandResult.RoutingType.REDIRECT);
    }
}

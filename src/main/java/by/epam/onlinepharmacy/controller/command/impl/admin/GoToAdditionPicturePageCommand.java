package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to addition picture page command.
 */
public class GoToAdditionPicturePageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        request.getSession().setAttribute(SessionAttribute.PRODUCT_ID, Long.parseLong(id));
        return new CommandResult(PagePath.ADDITION_PICTURE, CommandResult.RoutingType.REDIRECT);
    }
}

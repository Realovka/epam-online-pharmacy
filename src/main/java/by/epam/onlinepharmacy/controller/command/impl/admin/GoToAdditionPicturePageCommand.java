package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GoToAdditionPicturePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        request.getSession().setAttribute(SessionAttribute.PRODUCT_ID, Long.parseLong(id));
        return new CommandResult(PagePath.ADDITION_PICTURE, CommandResult.RoutingType.REDIRECT);
    }
}

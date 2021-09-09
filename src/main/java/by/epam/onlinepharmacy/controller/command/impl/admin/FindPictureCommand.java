package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FindPictureCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        ProductServiceImpl service = ProductServiceImpl.getInstance();
        long id = (long) request.getSession().getAttribute(SessionAttribute.PRODUCT_ID);
        String pathToFile = service.find(id);
        request.getSession().setAttribute(SessionAttribute.PATH_TO_FILE, pathToFile);
        return new CommandResult(PagePath.VIEW_PICTURE, CommandResult.RoutingType.REDIRECT);
    }
}

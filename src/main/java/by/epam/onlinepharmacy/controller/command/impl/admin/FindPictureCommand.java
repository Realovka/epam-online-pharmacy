package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class FindPictureCommand implements Command {
    private Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        ProductServiceImpl service = ProductServiceImpl.getInstance();
        long id = (long) request.getSession().getAttribute(SessionAttribute.PRODUCT_ID);
        String path;
        Optional<String> pathToFile;
        try {
            pathToFile = service.findPathToPicture(id);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        path = pathToFile.get();
        request.getSession().setAttribute(SessionAttribute.PATH_TO_FILE, path);
        return new CommandResult(PagePath.VIEW_PICTURE, CommandResult.RoutingType.REDIRECT);
    }
}

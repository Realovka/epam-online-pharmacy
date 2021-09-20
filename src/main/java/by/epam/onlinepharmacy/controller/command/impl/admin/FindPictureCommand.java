package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class FindPictureCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        ProductService productService = ProductServiceImpl.getInstance();
        long id = (long) request.getSession().getAttribute(SessionAttribute.PRODUCT_ID);
        String path;
        Optional<String> pathToFile;
        String productId = String.valueOf(id);
        ProductDto product;

        try {
            pathToFile = productService.findPathToPicture(id);
           product = productService.findProductById(productId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find path to picture ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        path = pathToFile.get();
        request.getSession().setAttribute(SessionAttribute.PATH_TO_FILE, path);
        request.getSession().setAttribute(SessionAttribute.PRODUCT, product);
        return new CommandResult(PagePath.VIEW_PICTURE, CommandResult.RoutingType.REDIRECT);
    }
}

package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class GoToAboutProductPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        HttpSession session = request.getSession();
        ProductService productService = ProductServiceImpl.getInstance();
        ProductDto productDto;
        try {
            productDto = productService.findProductById(id);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute while find product by id ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }


            session.setAttribute(SessionAttribute.PRODUCT, productDto);
            return new CommandResult(PagePath.ABOUT_PRODUCT, CommandResult.RoutingType.REDIRECT);

    }
}

package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchProductsByNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String productName = request.getParameter(RequestParameter.NAME_FOR_SEARCH_PRODUCTS);
        ProductService productService = ProductServiceImpl.getInstance();
        List<ProductDto> products;
        try {
            products = productService.findListProductsByName(productName);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list products by name ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.LIST_PRODUCTS_BY_NAME, products);
        return new CommandResult(PagePath.SEARCH_PRODUCTS_BY_NAME, CommandResult.RoutingType.REDIRECT);
    }
}

package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.*;
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
import java.util.LinkedHashMap;
import java.util.Map;

public class AdditionProductToOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        String url = request.getParameter(RequestParameter.CURRENT_URL);

        Map<Product, Integer> products;
        if (session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET) != null) {
            products = (Map<Product, Integer>) session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET);
        } else {
            products = new LinkedHashMap<>();
        }
        ProductService productService = ProductServiceImpl.getInstance();
        try {
            products = productService.addProductToOrder(id, products);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute add product to order ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET, products);
        return new CommandResult(url, CommandResult.RoutingType.REDIRECT);
    }
}

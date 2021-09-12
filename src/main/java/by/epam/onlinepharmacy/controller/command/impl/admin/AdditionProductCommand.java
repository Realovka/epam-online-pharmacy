package by.epam.onlinepharmacy.controller.command.impl.admin;

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
import java.util.List;
import java.util.Map;


public class AdditionProductCommand implements Command {
    private Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductService productService = ProductServiceImpl.getInstance();
        String name = request.getParameter(RequestParameter.NAME);
        String group = request.getParameter(RequestParameter.GROUP);
        String price = request.getParameter(RequestParameter.PRICE);
        String recipe = request.getParameter(RequestParameter.RECIPE);
        String instruction = request.getParameter(RequestParameter.INSTRUCTION);
        List<Product> products;


        Map<String, String> productParameters = productService.isValidParameters(name, group, price, instruction);
        if (productParameters.get(RequestParameter.NAME).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_NAME_ERROR, BundleKey.PRODUCT_NAME_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }

        if (productParameters.get(RequestParameter.GROUP).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_GROUP_ERROR, BundleKey.PRODUCT_GROUP_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }

        if (productParameters.get(RequestParameter.PRICE).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_PRICE_ERROR, BundleKey.PRODUCT_PRICE_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }

        if (productParameters.get(RequestParameter.INSTRUCTION).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_INSTRUCTION_ERROR, BundleKey.PRODUCT_INSTRUCTION_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }

        try {
            productService.createProduct(name,group,price,recipe, instruction);
            products = productService.findAllProducts();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PRODUCTS, products);
        return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.REDIRECT);
    }
}

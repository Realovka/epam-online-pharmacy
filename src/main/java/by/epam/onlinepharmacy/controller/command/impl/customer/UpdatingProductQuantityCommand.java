package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.BundleKey;
import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestAttribute;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import by.epam.onlinepharmacy.model.validation.BasketValidator;
import by.epam.onlinepharmacy.model.validation.impl.BasketValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The type Updating product quantity command.
 */
public class UpdatingProductQuantityCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        BasketValidator basketValidator = BasketValidatorImpl.getInstance();
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        String quantity = request.getParameter(RequestParameter.QUANTITY);

        if(!basketValidator.isValidProductQuantity(quantity)) {
            request.setAttribute(RequestAttribute.PRODUCT_QUANTITY_ERROR, BundleKey.PRODUCT_QUANTITY_ERROR);
            return new CommandResult(PagePath.BASKET, CommandResult.RoutingType.FORWARD);
        }

        HttpSession session = request.getSession();
        Map<Product, Integer> products = (Map<Product, Integer>) session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET);
        ProductService productService = ProductServiceImpl.getInstance();
        productService.updateProductQuantityInOrder(productId, quantity, products);
        return new CommandResult(PagePath.BASKET, CommandResult.RoutingType.REDIRECT);
    }
}

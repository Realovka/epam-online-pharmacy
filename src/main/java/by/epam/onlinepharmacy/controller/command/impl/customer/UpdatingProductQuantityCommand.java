package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class UpdatingProductQuantityCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        String quantity = request.getParameter(RequestParameter.QUANTITY);
        HttpSession session = request.getSession();
        Map<Product, Integer> order = (Map<Product, Integer>) session.getAttribute(SessionAttribute.ORDER);
        ProductService productService = ProductServiceImpl.getInstance();
        productService.updateProductQuantityInOrder(productId, quantity, order);
        return new CommandResult(PagePath.BASKET, CommandResult.RoutingType.REDIRECT);
    }
}

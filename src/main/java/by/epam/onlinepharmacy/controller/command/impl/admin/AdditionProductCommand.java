package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


public class AdditionProductCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        ProductService productService = ProductServiceImpl.getInstance();
        String name = request.getParameter(RequestParameter.NAME);
        String group = request.getParameter(RequestParameter.GROUP);
        String price = request.getParameter(RequestParameter.PRICE);
        String recipe = request.getParameter(RequestParameter.RECIPE);
        String instruction = request.getParameter(RequestParameter.INSTRUCTION);
        List<Product> products;
        try {
            productService.createProduct(name,group,price,recipe, instruction);
            products = productService.findAllProducts();
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        request.getSession().setAttribute(SessionAttribute.ALL_PRODUCTS, products);
        return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.REDIRECT);
    }
}

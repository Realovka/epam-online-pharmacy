package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import by.epam.onlinepharmacy.model.validation.ProductValidator;
import by.epam.onlinepharmacy.model.validation.impl.ProductValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdatingProductInstructionCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long productId = (long) session.getAttribute(SessionAttribute.PRODUCT_ID);
        String newInstruction = request.getParameter(RequestParameter.UPDATING_PRODUCT_NAME);
        ProductService productService = ProductServiceImpl.getInstance();
        ProductValidator productValidator = ProductValidatorImpl.getInstance();
        if(!productValidator.isValidInstruction(newInstruction)) {
            request.setAttribute(RequestAttribute.PRODUCT_INSTRUCTION_ERROR, BundleKey.PRODUCT_INSTRUCTION_ERROR);
            return new CommandResult(PagePath.UPDATING_PRODUCT_INSTRUCTION, CommandResult.RoutingType.FORWARD);
        }
        List<ProductDto> products;
        try {
            productService.updateProductInstruction(productId, newInstruction);
            products = productService.findAllProducts();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find all pharmacists or update instruction of product", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_PRODUCTS, products);
        return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.REDIRECT);
    }
}

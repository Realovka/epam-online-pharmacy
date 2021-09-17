package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.dto.ProductDto;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class GoToUpdatingProductInstructionPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        long id = Long.parseLong(productId);
        request.getSession().setAttribute(SessionAttribute.PRODUCT_ID, id);
        ProductService productService = ProductServiceImpl.getInstance();
        try {
            ProductDto productDto = productService.findInstructionByProductId(id);
            request.setAttribute(RequestAttribute.PRODUCT, productDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException while find ProductDto by productId ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return new CommandResult(PagePath.UPDATING_PRODUCT_INSTRUCTION, CommandResult.RoutingType.FORWARD);
    }
}

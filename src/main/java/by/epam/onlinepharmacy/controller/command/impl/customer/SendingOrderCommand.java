package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.OrderService;
import by.epam.onlinepharmacy.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class SendingOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long pharmacyId = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        OrderService orderService = OrderServiceImpl.getInstance();
        User auth =(User) session.getAttribute(SessionAttribute.USER_AUTH);
        long userId = auth.getUserId();
        Map<Product, Integer> products = (Map<Product, Integer>) session.getAttribute(SessionAttribute.PRODUCTS);
        try {
            orderService.createOrder(pharmacyId, userId, products);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while create order ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
       return new CommandResult(PagePath.ORDER_ACCEPT, CommandResult.RoutingType.REDIRECT);
    }
}

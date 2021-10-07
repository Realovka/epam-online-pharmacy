package by.epam.onlinepharmacy.controller.command.impl.pharmacist;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.StatusOrder;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.OrderService;
import by.epam.onlinepharmacy.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BasketForOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        OrderService orderService = OrderServiceImpl.getInstance();
        List<Basket> basket;
        Order order;
        try {
            basket = orderService.findBasketForOrder(orderId);
            order = orderService.findOrderById(orderId);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find all products in order or find order", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.BASKET, basket);
        session.setAttribute(SessionAttribute.ORDER, order);
        if(order.getStatusOrder().equals(StatusOrder.PROCESSING)) {
            return new CommandResult(PagePath.BASKET_FOR_ORDER_STATUS_PROCESSING, CommandResult.RoutingType.REDIRECT);
        } else {
            return new CommandResult(PagePath.BASKET_FOR_ORDER_STATUS_PREPARED, CommandResult.RoutingType.REDIRECT);
        }
    }
}

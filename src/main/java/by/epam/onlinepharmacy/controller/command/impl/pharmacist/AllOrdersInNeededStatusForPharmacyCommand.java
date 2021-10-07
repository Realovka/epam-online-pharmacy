package by.epam.onlinepharmacy.controller.command.impl.pharmacist;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.OrderService;
import by.epam.onlinepharmacy.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllOrdersInNeededStatusForPharmacyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String statusId = request.getParameter(RequestParameter.ORDER_STATUS_ID);
        long pharmacyId = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        OrderService orderService = OrderServiceImpl.getInstance();
        List<Order> orders;
        try {
            orders = orderService.findAllOrdersInNeededStatusForPharmacies(pharmacyId, statusId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find all orders in needed status ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.LIST_ORDERS_IN_NEEDED_STATUS, orders);
        if (statusId.equals("1")) {
            return new CommandResult(PagePath.ALL_PROCESSING_ORDERS, CommandResult.RoutingType.REDIRECT);
        } else {
            return new CommandResult(PagePath.ALL_PREPARED_ORDERS, CommandResult.RoutingType.REDIRECT);
        }
    }
}

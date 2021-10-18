package by.epam.onlinepharmacy.controller.listener;

import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.OrderService;
import by.epam.onlinepharmacy.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

/**
 * The type Delete order timer task.
 */
public class DeleteOrderTimerTask extends TimerTask {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void run() {
        OrderService orderService = OrderServiceImpl.getInstance();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));
        try {
            orderService.deleteOrders(timestamp);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method run while delete orders ", e);
        }
    }
}

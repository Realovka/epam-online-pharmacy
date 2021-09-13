package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.impl.OrderDaoImpl;
import by.epam.onlinepharmacy.model.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class OrderServiceImpl implements OrderService {
    private Logger logger = LogManager.getLogger();

    private OrderServiceImpl() {
    }
    private static OrderServiceImpl instance = new OrderServiceImpl();
    public OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void createOrder(long pharmacyId) throws ServiceException {
       Order order = new Order.Builder()
               .setDataStarting(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())))
               .setDataEnding(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).plusHours(24)))
               .setPharmacyId(pharmacyId)
               .build();
        try {
            orderDao.createOrder(order);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createOrder() ", e);
            throw new ServiceException("DaoException is in method createOrder() ", e);
        }
    }
}

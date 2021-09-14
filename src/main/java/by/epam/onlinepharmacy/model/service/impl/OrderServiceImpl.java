package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.impl.OrderDaoImpl;
import by.epam.onlinepharmacy.model.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void createOrder(long pharmacyId, long userId, Map<Product, Integer> products) throws ServiceException {
        Order order = new Order.Builder()
                .setDataStarting(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())))
                .setDataEnding(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).plusHours(24)))
                .setPharmacyId(pharmacyId)
                .build();
        try {
            long orderId = orderDao.createOrder(order);
            List<Basket> basketList = new ArrayList<>();
            products.entrySet().forEach(product -> basketList.add(new Basket.Builder()
                    .setUserId(userId)
                    .setProductId(product.getKey().getProductId())
                    .setOrderId(orderId)
                    .setQuantity(product.getValue())
                    .build()));
            orderDao.createProductsInBasket(basketList);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createOrder() ", e);
            throw new ServiceException("DaoException is in method createOrder() ", e);
        }
    }
}

package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.*;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.OrderDao;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.impl.OrderDaoImpl;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
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
import java.util.Optional;

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
    public void createOrder(long pharmacyId, User auth, Map<Product, Integer> products) throws ServiceException {
        PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();
        Optional<Pharmacy> pharmacyDb;
        try {
         pharmacyDb = pharmacyDao.findPharmacyById(pharmacyId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPharmacyByIdr() ", e);
            throw new ServiceException("DaoException is in method findPharmacyById() ", e);
        }
        //TODO
        Pharmacy pharmacy = pharmacyDb.orElse(new Pharmacy());
        Order order = new Order.Builder()
                .setDataStarting(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())))
                .setDataEnding(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).plusHours(24)))
                .setPharmacy(pharmacy)
                .build();
        Order orderDB;
        try {
            orderDB = orderDao.createOrder(order);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createOrder() ", e);
            throw new ServiceException("DaoException is in method createOrder() ", e);
        }
        try {
            List<Basket> basketList = new ArrayList<>();
            products.entrySet().forEach(product -> basketList.add(new Basket.Builder()
                    .setUser(auth)
                    .setProduct(product.getKey())
                    .setOrder(orderDB)
                    .setQuantity(product.getValue())
                    .build()));
            orderDao.createProductsInBasket(basketList);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createProductsInBasket() ", e);
            throw new ServiceException("DaoException is in method createProductsInBasket() ", e);
        }
    }

    @Override
    public List<Order> findAllProcessingOrdersForPharmacies(String pharmacyId) throws ServiceException {
        long id = Long.parseLong(pharmacyId);
        List<Order> orders;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
         orders = orderDao.findAllProcessingOrdersForPharmacies(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllProcessingOrdersForPharmacies() ", e);
            throw new ServiceException("DaoException is in method findAllProcessingOrdersForPharmacies() ", e);
        }
        return orders;
    }

    @Override
    public List<Basket> findBasketForOrder(String orderId) throws ServiceException {
        long id = Long.parseLong(orderId);
        List<Basket> basket;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            basket = orderDao.findBasketForOrder(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllProductsInOrder() ", e);
            throw new ServiceException("DaoException is in method findAllProductsInOrder() ", e);
        }
        return basket;
    }
}

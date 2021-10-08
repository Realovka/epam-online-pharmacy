package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.*;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.OrderDao;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.UserDao;
import by.epam.onlinepharmacy.model.dao.impl.OrderDaoImpl;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
import by.epam.onlinepharmacy.model.dao.impl.UserDaoImpl;
import by.epam.onlinepharmacy.model.service.OrderService;
import by.epam.onlinepharmacy.model.verification.EmailSending;
import by.epam.onlinepharmacy.model.verification.impl.EmailSendingImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private Logger logger = LogManager.getLogger();
    private static final String HEADER_FOR_PREPARED_ORDER = "Information about your order";
    private static final String MESSAGE_FOR_PREPARED_ORDER = """
            Hello, %s  %s! Your order number %s is prepared.
            """;
    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();
    private EmailSending emailSending = EmailSendingImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    private static OrderServiceImpl instance = new OrderServiceImpl();

    public static OrderServiceImpl getInstance() {
        return instance;
    }


    @Override
    public void createOrder(long pharmacyId, User auth, Map<Product, Integer> products) throws ServiceException {
        Optional<Pharmacy> pharmacyDb;
        try {
            pharmacyDb = pharmacyDao.findPharmacyById(pharmacyId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPharmacyByIdr() ", e);
            throw new ServiceException("DaoException is in method findPharmacyById() ", e);
        }
        //TODO
        Pharmacy pharmacy = pharmacyDb.orElse(new Pharmacy());
        LocalDate date = LocalDate.now();
        Order order = new Order.Builder()
                .setDataStarting(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())))
                .setDataEnding(Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT).plusDays(1)))
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
    public List<Order> findAllOrdersInNeededStatusForPharmacies(long pharmacyId, String statusId) throws ServiceException {
        int statusOrderId = Integer.parseInt(statusId);
        List<Order> orders;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            orders = orderDao.findAllProcessingOrdersForPharmacies(pharmacyId, statusOrderId);
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

    @Override
    public Order findOrderById(String orderId) throws ServiceException {
        long id = Long.parseLong(orderId);
        Optional<Order> orderDb;
        try {
            orderDb = orderDao.findOrderById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findOrderById() ", e);
            throw new ServiceException("DaoException is in method findOrderById() ", e);
        }
        Order order = orderDb.orElse(new Order());
        return order;
    }

    @Override
    public Order updateStatusOrder(String statusOrderId, String orderId, Basket basket) throws ServiceException {
        int statusOrder = Integer.parseInt(statusOrderId);
        long id = Long.parseLong(orderId);
        Optional<Order> orderDb;
        try {
            orderDao.updateStatusOrder(statusOrder, id);
            orderDb = orderDao.findOrderById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method  updateStatusOrder() ", e);
            throw new ServiceException("DaoException is in method updateStatusOrder() ", e);
        }
        Order order = orderDb.orElse(new Order());
        if (statusOrder == 2) {
            User whoDidOrder = basket.getUser();
            sendConfirmingEmailToCustomer(whoDidOrder, orderId);
        }
        return order;
    }

    @Override
    public void deleteOrders(Timestamp timestamp) throws ServiceException {
        try {
            orderDao.deleteOrders(timestamp);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method deleteOrders() ", e);
            throw new ServiceException("DaoException is in method deleteOrders() ", e);
        }
    }

    private void sendConfirmingEmailToCustomer(User user, String orderId) {
        emailSending.sendEmail(user, orderId, HEADER_FOR_PREPARED_ORDER, MESSAGE_FOR_PREPARED_ORDER);
    }
}

package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.OrderDao;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.impl.OrderDaoImpl;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
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
            Hello, %s  %s! Your order number %s is prepared. You can pick up your order today before the end
            of the working day of the pharmacy number %s at address: the city  %s, the street %s, house %s.
            """;
    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();
    private EmailSending emailSending = EmailSendingImpl.getInstance();


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
        Pharmacy pharmacy = pharmacyDb.get();
        LocalDate date = LocalDate.now();
        Order order = new Order.Builder()
                .setDataStarting(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())))
                .setDataEnding(Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT).plusDays(1)))
                .setPharmacy(pharmacy)
                .build();
        int orderId;
        try {
            orderId = orderDao.createOrder(order);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createOrder() ", e);
            throw new ServiceException("DaoException is in method createOrder() ", e);
        }
        try {
            Order newOrder = new Order.Builder()
                    .setOrderId(orderId)
                    .build();
            List<Basket> basketList = new ArrayList<>();
            products.entrySet().forEach(product -> basketList.add(new Basket.Builder()
                    .setUser(auth)
                    .setProduct(product.getKey())
                    .setOrder(newOrder)
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
            logger.log(Level.ERROR, "DaoException is in method  findAllOrdersInNeededStatusForPharmacies() ", e);
            throw new ServiceException("DaoException is in method findAllOrdersInNeededStatusForPharmacies() ", e);
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
            logger.log(Level.ERROR, "DaoException is in method findBasketForOrder() ", e);
            throw new ServiceException("DaoException is in method findBasketForOrder() ", e);
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
        Order order = orderDb.get();
        return order;
    }

    @Override
    public Order updateStatusOrder(String statusOrderId, String orderId, Basket basket) throws ServiceException {
        int statusOrder = Integer.parseInt(statusOrderId);
        long id = Long.parseLong(orderId);
        Optional<Order> orderDb;
        Optional<Pharmacy> pharmacyDb;
        Order order;
        try {
            orderDao.updateStatusOrder(statusOrder, id);
            orderDb = orderDao.findOrderById(id);
            order = orderDb.get();
            pharmacyDb = pharmacyDao.findPharmacyById(order.getPharmacy().getPharmacyId());
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateStatusOrder() ", e);
            throw new ServiceException("DaoException is in method updateStatusOrder() ", e);
        }

        if (statusOrder == 2) {
            User whoDidOrder = basket.getUser();
            Pharmacy pharmacy = pharmacyDb.get();
            sendConfirmingEmailToCustomer(whoDidOrder, orderId, pharmacy);
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

    private void sendConfirmingEmailToCustomer(User user, String orderId, Pharmacy pharmacy) {
        emailSending.sendEmailPrepareOrder(user, orderId, pharmacy, HEADER_FOR_PREPARED_ORDER, MESSAGE_FOR_PREPARED_ORDER);
    }
}

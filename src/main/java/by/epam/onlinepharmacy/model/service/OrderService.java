package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Create order.
     *
     * @param pharmacyId the pharmacy id
     * @param auth       the auth
     * @param products   the products
     * @throws ServiceException the service exception
     */
    void createOrder(long pharmacyId, User auth, Map<Product, Integer> products) throws ServiceException;

    /**
     * Find all orders in needed status for pharmacies list.
     *
     * @param pharmacyId the pharmacy id
     * @param statusId   the status id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllOrdersInNeededStatusForPharmacies(long pharmacyId, String statusId) throws ServiceException;

    /**
     * Find basket for order list.
     *
     * @param orderId the order id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Basket> findBasketForOrder(String orderId) throws ServiceException;

    /**
     * Find order by id order.
     *
     * @param orderId the order id
     * @return the order
     * @throws ServiceException the service exception
     */
    Order findOrderById(String orderId) throws ServiceException;

    /**
     * Update status order order.
     *
     * @param statusOrderId the status order id
     * @param orderId       the order id
     * @param basket        the basket
     * @return the order
     * @throws ServiceException the service exception
     */
    Order updateStatusOrder(String statusOrderId, String orderId, Basket basket) throws ServiceException;

    /**
     * Delete orders.
     *
     * @param timestamp the timestamp
     * @throws ServiceException the service exception
     */
    void deleteOrders(Timestamp timestamp) throws ServiceException;
}

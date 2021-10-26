package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.DaoException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


/**
 * The interface Order dao.
 */
public interface OrderDao {
    /**
     * Create order.
     *
     * @param order order
     * @return the int
     * @throws DaoException the dao exception
     */
    int createOrder(Order order) throws DaoException;


    /**
     * Create products in basket.
     *
     * @param basket the basket
     * @throws DaoException the dao exception
     */
    void createProductsInBasket(List<Basket> basket) throws DaoException;

    /**
     * Find all processing orders for pharmacy list.
     *
     * @param pharmacyId the pharmacy id
     * @param statusId   the status id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllProcessingOrdersForPharmacy(long pharmacyId, int statusId) throws DaoException;

    /**
     * Find basket for order list.
     *
     * @param orderId the order id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Basket> findBasketForOrder(long orderId) throws DaoException;


    /**
     * Find order by id optional.
     *
     * @param orderId the order id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Order> findOrderById(long orderId) throws DaoException;


    /**
     * Update status order int.
     *
     * @param statusId the status id
     * @param orderId  the order id
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateStatusOrder(int statusId, long orderId) throws DaoException;


    /**
     * Delete orders int.
     *
     * @param timestamp the timestamp
     * @return the int
     * @throws DaoException the dao exception
     */
    int deleteOrders(Timestamp timestamp) throws DaoException;

}

package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.DaoException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order createOrder(Order order) throws DaoException;
    void createProductsInBasket(List<Basket> basket) throws DaoException;
    List<Order> findAllProcessingOrdersForPharmacies(long pharmacyId, int statusId) throws DaoException;
    List<Basket> findBasketForOrder(long orderId) throws DaoException;
    Optional<Order> findOrderById(long orderId) throws DaoException;
    void updateStatusOrder(int statusId, long orderId) throws DaoException;
    void deleteOrders(Timestamp timestamp) throws DaoException;

}

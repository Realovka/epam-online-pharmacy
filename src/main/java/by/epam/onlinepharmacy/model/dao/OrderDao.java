package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;

public interface OrderDao {
    Order createOrder(Order order) throws DaoException;
    void createProductsInBasket(List<Basket> basket) throws DaoException;
    List<Order> findAllProcessingOrdersForPharmacies(long pharmacyId) throws DaoException;
    List<Basket> findBasketForOrder(long orderId) throws DaoException;
}

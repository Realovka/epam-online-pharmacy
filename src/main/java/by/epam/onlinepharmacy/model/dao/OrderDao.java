package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;

public interface OrderDao {
    long createOrder(Order order) throws DaoException;
    void createProductsInBasket(List<Basket> basket) throws DaoException;
}

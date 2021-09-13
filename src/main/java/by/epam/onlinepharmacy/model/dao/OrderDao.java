package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.exception.DaoException;

public interface OrderDao {
    void createOrder(Order order) throws DaoException;
}

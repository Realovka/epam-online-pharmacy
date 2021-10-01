package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void createOrder(long pharmacyId, User auth, Map<Product, Integer> products) throws ServiceException;
    List<Order> findAllProcessingOrdersForPharmacies(String pharmacyId) throws ServiceException;
    List<Basket> findBasketForOrder(String orderId) throws ServiceException;
}

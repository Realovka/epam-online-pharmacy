package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.Map;

public interface OrderService {
    void createOrder(long pharmacyId, long userId, Map<Product, Integer> products) throws ServiceException;
}

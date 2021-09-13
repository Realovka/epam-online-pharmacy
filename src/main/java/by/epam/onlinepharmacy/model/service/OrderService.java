package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.exception.ServiceException;

public interface OrderService {
    void createOrder(long pharmacyId) throws ServiceException;
}

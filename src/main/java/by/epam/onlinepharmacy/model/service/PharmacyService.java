package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;

public interface PharmacyService {
    List<Pharmacy> findAllPharmacies() throws ServiceException;
    void createPharmacy(String number, String city, String street, String house, String block) throws ServiceException;
}

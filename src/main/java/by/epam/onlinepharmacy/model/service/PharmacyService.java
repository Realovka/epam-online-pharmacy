package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface PharmacyService {
    List<Pharmacy> findAllPharmacies() throws ServiceException;
    void createPharmacy(String number, String city, String street, String house, String block) throws ServiceException;
    Map<String, String> isFormValid(String number, String city, String street, String house, String block);
}

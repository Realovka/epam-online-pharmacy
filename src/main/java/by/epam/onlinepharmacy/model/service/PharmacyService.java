package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;

public interface PharmacyService {
    List<Pharmacy> findAllPharmacies() throws ServiceException;
    void createPharmacy(Pharmacy pharmacy) throws ServiceException;
}

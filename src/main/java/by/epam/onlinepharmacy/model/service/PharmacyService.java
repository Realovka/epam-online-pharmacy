package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PharmacyService {
    List<Pharmacy> findAllPharmacies(int startingPharmacy) throws ServiceException;
    void createPharmacy(String number, String city, String street, String house, String block) throws ServiceException;
    Map<String, String> isFormValid(String number, String city, String street, String house, String block);
    Optional<Pharmacy> findPharmacyById(long id) throws ServiceException;
    void updateNumber(long id, String number) throws ServiceException;
    void updateCity(long id, String city) throws ServiceException;
    void updateStreet(long id, String street) throws ServiceException;
    void updateHouse(long id, String house) throws ServiceException;
    void updateBlock(long id, String block) throws ServiceException;
}

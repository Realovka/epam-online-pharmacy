package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.dto.PharmacyDto;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface PharmacyService {
    List<PharmacyDto> findListPharmacies(int startingPharmacy) throws ServiceException;
    List<PharmacyDto> findListPharmaciesByCity(String city) throws ServiceException;
    List<PharmacyDto> createPharmacy(String number, String city, String street, String house, String block) throws ServiceException;
    int findCurrentPage() throws ServiceException;
    Map<String, String> isFormValid(String number, String city, String street, String house, String block);
    PharmacyDto findPharmacyById(long id) throws ServiceException;
    void updateNumber(long id, String number) throws ServiceException;
    void updateCity(long id, String city) throws ServiceException;
    void updateStreet(long id, String street) throws ServiceException;
    void updateHouse(long id, String house) throws ServiceException;
    void updateBlock(long id, String block) throws ServiceException;
}

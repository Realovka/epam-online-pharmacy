package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.dto.PharmacyRegDto;
import by.epam.onlinepharmacy.dto.PharmacyViewDto;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;

public interface PharmacyService {
    List<PharmacyViewDto> findAllPharmacies() throws ServiceException;
    void createPharmacy(PharmacyRegDto pharmacy) throws ServiceException;
}

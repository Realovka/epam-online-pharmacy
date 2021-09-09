package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.validation.impl.PharmacyValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.onlinepharmacy.controller.command.RequestParameter.*;


public class PharmacyServiceImpl implements PharmacyService {
    private Logger logger = LogManager.getLogger();
    private static final String ZERO_STRING = "0";
    private static final String EMPTY_STRING = "\s";
    private static PharmacyServiceImpl instance = new PharmacyServiceImpl();
    private PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();

    private PharmacyServiceImpl() {
    }

    public static PharmacyServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Pharmacy> findAllPharmacies() throws ServiceException {
        List<Pharmacy> pharmacies;
        try {
            pharmacies = pharmacyDao.findAllPharmacies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method findAllPharmacies() ", e);
            throw new ServiceException("Exception is in method findAllPharmacies() ", e);
        }

        //TODO
        for (Pharmacy item : pharmacies) {
            if (item.getBlock() == 0) {
                item.setBlock(null);
            }
        }
        return pharmacies;
    }

    @Override
    public void createPharmacy(String number, String city, String street, String house, String block) throws ServiceException {
        Pharmacy pharmacy = new Pharmacy.Builder()
                .setNumber(Integer.parseInt(number))
                .setCity(city)
                .setStreet(street)
                .setHouse(house)
                .build();
        if (block.isBlank()) {
            pharmacy.setBlock(0);
        } else {
            pharmacy.setBlock(Integer.valueOf(block));
        }
        try {
            pharmacyDao.createPharmacy(pharmacy);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method createPharmacy() ", e);
            throw new ServiceException("Exception is in method createPharmacy() ", e);
        }
    }

    public Map<String, String> isFormValid(String number, String city, String street, String house, String block) {
        Map<String, String> pharmacyParameters = new HashMap<>();
        pharmacyParameters.put(NUMBER, number);
        pharmacyParameters.put(CITY, city);
        pharmacyParameters.put(STREET, street);
        pharmacyParameters.put(HOUSE, house);
        pharmacyParameters.put(BLOCK, block);
        PharmacyValidatorImpl.getInstance().isValidForm(pharmacyParameters);

        if (pharmacyParameters.get(RequestParameter.BLOCK).equals(ZERO_STRING)) {
            pharmacyParameters.put(RequestParameter.BLOCK, EMPTY_STRING);
        }
        return pharmacyParameters;
    }
}

package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.validation.impl.PharmacyValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.onlinepharmacy.controller.command.RequestParameter.*;


public class PharmacyServiceImpl implements PharmacyService {
    private Logger logger = LogManager.getLogger();
    private static final String ZERO_STRING = "0";
    private static final String BLANK_STRING = "\s";
    private static final int RECORD_PER_PAGE = 5;
    private static PharmacyServiceImpl instance = new PharmacyServiceImpl();
    private PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();

    private PharmacyServiceImpl() {
    }

    public static PharmacyServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Pharmacy> findAllPharmacies(int startingPharmacy) throws ServiceException {
        List<Pharmacy> pharmacies;
        try {
            pharmacies = pharmacyDao.findPharmacies(startingPharmacy);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllPharmacies() ", e);
            throw new ServiceException("DaoException is in method findAllPharmacies() ", e);
        }

        changePharmaciesBlocks(pharmacies);

        return pharmacies;
    }

    @Override
    public List<Pharmacy> createPharmacy(String number, String city, String street, String house, String block) throws ServiceException {
        List<Pharmacy> pharmacies;
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
            int pharmaciesNumber = pharmacyDao.findPharmaciesNumber();
            int pharmaciesOnLastPage = pharmaciesNumber % RECORD_PER_PAGE;
            int pages = pharmaciesNumber / RECORD_PER_PAGE;
            if (pharmaciesOnLastPage == 0) {
                pharmacies = pharmacyDao.findPharmacies(pages * RECORD_PER_PAGE - RECORD_PER_PAGE);
            } else {
                pharmacies = pharmacyDao.findPharmacies(pages * RECORD_PER_PAGE);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createPharmacy() ", e);
            throw new ServiceException("DaoException is in method createPharmacy() ", e);
        }
        changePharmaciesBlocks(pharmacies);
        return pharmacies;
    }

    @Override
    public int findCurrentPage() throws ServiceException {
        int pharmaciesNumber;
        int currentPage;
        try {
            pharmaciesNumber = pharmacyDao.findPharmaciesNumber();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findCurrentPage() ", e);
            throw new ServiceException("DaoException is in method findCurrentPage() ", e);
        }
        if (pharmaciesNumber % RECORD_PER_PAGE == 0) {
            currentPage = pharmaciesNumber / RECORD_PER_PAGE;
        } else {
            currentPage = pharmaciesNumber / RECORD_PER_PAGE + 1;
        }
        return currentPage;
    }

    @Override
    public Map<String, String> isFormValid(String number, String city, String street, String house, String block) {
        Map<String, String> pharmacyParameters = new HashMap<>();
        pharmacyParameters.put(NUMBER, number);
        pharmacyParameters.put(CITY, city);
        pharmacyParameters.put(STREET, street);
        pharmacyParameters.put(HOUSE, house);
        pharmacyParameters.put(BLOCK, block);
        PharmacyValidatorImpl.getInstance().isValidForm(pharmacyParameters);

        if (pharmacyParameters.get(RequestParameter.BLOCK).equals(ZERO_STRING)) {
            pharmacyParameters.put(RequestParameter.BLOCK, BLANK_STRING);
        }
        return pharmacyParameters;
    }

    @Override
    public Optional<Pharmacy> findPharmacyById(long id) throws ServiceException {
        Optional<Pharmacy> pharmacy;
        try {
            pharmacy = pharmacyDao.findPharmacyById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPharmacyById() ", e);
            throw new ServiceException("DaoException is in method findPharmacyById() ", e);
        }
        return pharmacy;
    }

    @Override
    public void updateNumber(long id, String number) throws ServiceException {
        int newNumber = Integer.parseInt(number);
        try {
            pharmacyDao.updateNumber(id, newNumber);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateNumber() ", e);
            throw new ServiceException("DaoException is in method updateNumber() ", e);
        }
    }

    @Override
    public void updateCity(long id, String city) throws ServiceException {
        try {
            pharmacyDao.updateCity(id, city);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateCity() ", e);
            throw new ServiceException("DaoException is in method updateCity() ", e);
        }
    }

    @Override
    public void updateStreet(long id, String street) throws ServiceException {
        try {
            pharmacyDao.updateStreet(id, street);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateStreet() ", e);
            throw new ServiceException("DaoException is in method updateStreet() ", e);
        }
    }

    @Override
    public void updateHouse(long id, String house) throws ServiceException {
        try {
            pharmacyDao.updateHouse(id, house);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateHouse() ", e);
            throw new ServiceException("DaoException is in method updateHouse() ", e);
        }
    }

    @Override
    public void updateBlock(long id, String block) throws ServiceException {
        if (block.isBlank()) {
            try {
                pharmacyDao.updateBlock(id, 0);
            } catch (DaoException e) {
                logger.log(Level.ERROR, "DaoException is in method updateBlock() ", e);
                throw new ServiceException("DaoException is in method updateBlock() ", e);
            }
            return;
        }

        int newBlock = Integer.parseInt(block);
        try {
            pharmacyDao.updateBlock(id, newBlock);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateBlock() ", e);
            throw new ServiceException("DaoException is in method updateBlock() ", e);
        }
    }

    private List<Pharmacy> changePharmaciesBlocks(List<Pharmacy> pharmacies) {
        for (Pharmacy item : pharmacies) {
            if (item.getBlock() == 0) {
                item.setBlock(null);
            }
        }
        return pharmacies;
    }

}

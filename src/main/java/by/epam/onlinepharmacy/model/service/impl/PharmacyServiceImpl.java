package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.dto.PharmacyDto;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.validation.PharmacyValidator;
import by.epam.onlinepharmacy.model.validation.impl.PharmacyValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.epam.onlinepharmacy.controller.command.RequestParameter.BLOCK;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.CITY;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.HOUSE;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.NUMBER;
import static by.epam.onlinepharmacy.controller.command.RequestParameter.STREET;


public class PharmacyServiceImpl implements PharmacyService {
    private Logger logger = LogManager.getLogger();
    private static final String ZERO_STRING = "0";
    private static final String BLANK_STRING = "\s";
    private static final int RECORD_PER_PAGE = 15;
    private PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();
    private PharmacyValidator pharmacyValidator = PharmacyValidatorImpl.getInstance();

    private PharmacyServiceImpl() {
    }

    private static PharmacyServiceImpl instance = new PharmacyServiceImpl();

    public static PharmacyServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<PharmacyDto> findListPharmacies(int startingPharmacy) throws ServiceException {
        List<Pharmacy> pharmaciesDb;
        try {
            pharmaciesDb = pharmacyDao.findListPharmacies(startingPharmacy);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListPharmacies() while find list pharmacies ", e);
            throw new ServiceException("DaoException is in method findListPharmacies() while find list pharmacies ", e);
        }

        List<PharmacyDto> pharmacies = convertListPharmacyListToPharmacyDto(pharmaciesDb);
        return pharmacies;
    }

    @Override
    public List<PharmacyDto> findListPharmaciesByCity(String city) throws ServiceException {
        List<Pharmacy> pharmaciesDb;
        try {
            pharmaciesDb = pharmacyDao.findPharmaciesByCity(city);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListPharmaciesByCity() while find pharmacies by city ", e);
            throw new ServiceException("DaoException is in method findListPharmaciesByCity() while find pharmacies by city ", e);
        }

        List<PharmacyDto> pharmacies = convertListPharmacyListToPharmacyDto(pharmaciesDb);
        return pharmacies;
    }

    @Override
    public List<PharmacyDto> createPharmacy(String number, String city, String street, String house, String block) throws ServiceException {
        List<Pharmacy> currentPharmacies;
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
                currentPharmacies = pharmacyDao.findListPharmacies(pages * RECORD_PER_PAGE - RECORD_PER_PAGE);
            } else {
                currentPharmacies = pharmacyDao.findListPharmacies(pages * RECORD_PER_PAGE);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createPharmacy() while find list pharmacies ", e);
            throw new ServiceException("DaoException is in method createPharmacy() while find list pharmacies ", e);
        }
        List<PharmacyDto> pharmacies = convertListPharmacyListToPharmacyDto(currentPharmacies);
        return pharmacies;
    }

    @Override
    public int findCurrentPage() throws ServiceException {
        int pharmaciesNumber;
        int currentPage;
        try {
            pharmaciesNumber = pharmacyDao.findPharmaciesNumber();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findCurrentPage() while find pharmacies number ", e);
            throw new ServiceException("DaoException is in method findCurrentPage() while find pharmacies number ", e);
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
        pharmacyValidator.isValidForm(pharmacyParameters);

        if (pharmacyParameters.get(RequestParameter.BLOCK).equals(ZERO_STRING)) {
            pharmacyParameters.put(RequestParameter.BLOCK, BLANK_STRING);
        }
        return pharmacyParameters;
    }

    @Override
    public PharmacyDto findPharmacyById(long id) throws ServiceException {
        Pharmacy pharmacyDb;
        try {
            pharmacyDb = pharmacyDao.findPharmacyById(id).get();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPharmacyById() while find pharmacy by id ", e);
            throw new ServiceException("DaoException is in method findPharmacyById() while find pharmacy by id ", e);
        }
        PharmacyDto pharmacy = convertPharmacyToPharmacyDto(pharmacyDb);
        return pharmacy;
    }



    @Override
    public void updateNumber(long id, String number) throws ServiceException {
        int newNumber = Integer.parseInt(number);
        try {
            pharmacyDao.updateNumber(id, newNumber);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateNumber() while update number ", e);
            throw new ServiceException("DaoException is in method updateNumber() while update number ", e);
        }
    }

    @Override
    public void updateCity(long id, String city) throws ServiceException {
        try {
            pharmacyDao.updateCity(id, city);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateCity() while update city ", e);
            throw new ServiceException("DaoException is in method updateCity() while update city ", e);
        }
    }

    @Override
    public void updateStreet(long id, String street) throws ServiceException {
        try {
            pharmacyDao.updateStreet(id, street);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateStreet() while update street ", e);
            throw new ServiceException("DaoException is in method updateStreet() while update street ", e);
        }
    }

    @Override
    public void updateHouse(long id, String house) throws ServiceException {
        try {
            pharmacyDao.updateHouse(id, house);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateHouse() while update house ", e);
            throw new ServiceException("DaoException is in method updateHouse() while update house ", e);
        }
    }

    @Override
    public void updateBlock(long id, String block) throws ServiceException {
        if (block.isBlank()) {
            try {
                pharmacyDao.updateBlock(id, 0);
            } catch (DaoException e) {
                logger.log(Level.ERROR, "DaoException is in method updateBlock() while update block when new block 0 ", e);
                throw new ServiceException("DaoException is in method updateBlock() while update block when new block 0 ", e);
            }
            return;
        }

        int newBlock = Integer.parseInt(block);
        try {
            pharmacyDao.updateBlock(id, newBlock);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateBlock() while update block ", e);
            throw new ServiceException("DaoException is in method updateBlock() while update block ", e);
        }
    }

    private String convertBlock(Integer block) {
        return (block == 0) ? null : String.valueOf(block);
    }

    private List<PharmacyDto> convertListPharmacyListToPharmacyDto(List<Pharmacy> pharmacies) {
        return pharmacies.stream()
                .map(pharmacy -> new PharmacyDto.Builder()
                        .setPharmacyId(pharmacy.getPharmacyId())
                        .setNumber(pharmacy.getNumber())
                        .setCity(pharmacy.getCity())
                        .setStreet(pharmacy.getStreet())
                        .setHouse(pharmacy.getHouse())
                        .setBlock(convertBlock(pharmacy.getBlock()))
                        .build()).collect(Collectors.toList());
    }

    private PharmacyDto convertPharmacyToPharmacyDto(Pharmacy pharmacy) {
        return  new PharmacyDto.Builder()
                .setPharmacyId(pharmacy.getPharmacyId())
                .setNumber(pharmacy.getNumber())
                .setCity(pharmacy.getCity())
                .setStreet(pharmacy.getStreet())
                .setHouse(pharmacy.getHouse())
                .setBlock(convertBlock(pharmacy.getBlock()))
                .build();
    }

}

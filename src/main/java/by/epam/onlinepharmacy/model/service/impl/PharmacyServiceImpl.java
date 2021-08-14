package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.dto.PharmacyRegDto;
import by.epam.onlinepharmacy.dto.PharmacyViewDto;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacyServiceImpl implements PharmacyService {
    private Logger logger = LogManager.getLogger();
    private PharmacyDao pharmacyDao = new PharmacyDaoImpl();

    @Override
    public List<PharmacyViewDto> findAllPharmacies() throws ServiceException {
        List<Pharmacy> pharmacies;
        List<PharmacyViewDto> pharmaciesView;
        try {
            pharmacies = pharmacyDao.findAllPharmacies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method findAllPharmacies() " + e.getMessage());
            throw new ServiceException("Exception is in method findAllPharmacies() " + e.getMessage());
        }

        pharmaciesView = pharmacies.stream()
                .map(pharmacy -> new PharmacyViewDto.Builder()
                        .setPharmacyId(pharmacy.getPharmacyId())
                        .setNumber(pharmacy.getNumber())
                        .setCity(pharmacy.getCity())
                        .setStreet(pharmacy.getStreet())
                        .setHouse(pharmacy.getHouse())
                        .setBlock(pharmacy.getBlock())
                        .build()).collect(Collectors.toList());
        //TODO
        for (PharmacyViewDto item : pharmaciesView) {
            if (item.getBlock() == 0) {
                item.setBlock(null);
            }
        }
        return pharmaciesView;
    }

    @Override
    public void createPharmacy(PharmacyRegDto pharmacy) throws ServiceException {
        Pharmacy newPharmacy = new Pharmacy.Builder()
                .setNumber(Integer.parseInt(pharmacy.getNumber()))
                .setCity(pharmacy.getCity())
                .setStreet(pharmacy.getStreet())
                .setHouse(pharmacy.getHouse())
                .setBlock(Integer.parseInt(pharmacy.getBlock()))
                .build();

        try {
            pharmacyDao.createPharmacy(newPharmacy);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method createPharmacy() " + e.getMessage());
            throw new ServiceException("Exception is in method createPharmacy() " + e.getMessage());
        }
    }
}

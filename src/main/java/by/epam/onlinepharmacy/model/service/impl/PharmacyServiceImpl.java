package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import by.epam.onlinepharmacy.model.dao.impl.PharmacyDaoImpl;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PharmacyServiceImpl implements PharmacyService {
    private Logger logger = LogManager.getLogger();
    private PharmacyDao pharmacyDao = new PharmacyDaoImpl();

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
    public void createPharmacy(Pharmacy pharmacy) throws ServiceException {
        try {
            pharmacyDao.createPharmacy(pharmacy);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception is in method createPharmacy() ", e);
            throw new ServiceException("Exception is in method createPharmacy() ", e);
        }
    }
}

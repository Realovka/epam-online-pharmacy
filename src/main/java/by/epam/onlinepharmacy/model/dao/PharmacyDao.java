package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;

public interface PharmacyDao {
    List<Pharmacy> findAllPharmacies() throws DaoException;
    void createPharmacy(Pharmacy pharmacy) throws DaoException;

}

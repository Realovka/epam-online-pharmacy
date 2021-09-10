package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface PharmacyDao {
    List<Pharmacy> findAllPharmacies() throws DaoException;
    void createPharmacy(Pharmacy pharmacy) throws DaoException;
    Optional<Pharmacy> findPharmacyById(long id) throws DaoException;
    void updateNumber(long id, int number) throws DaoException;
    void updateCity(long id, String city) throws DaoException;
    void updateStreet(long id, String street) throws DaoException;
    void updateHouse(long id, String house) throws DaoException;
    void updateBlock(long id, int block) throws DaoException;

}

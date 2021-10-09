package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface PharmacyDao {
    List<Pharmacy> findListPharmacies(int startingPharmacy) throws DaoException;
    List<Pharmacy> findPharmaciesByCity(String city) throws DaoException;
    boolean createPharmacy(Pharmacy pharmacy) throws DaoException;
    Optional<Pharmacy> findPharmacyById(long id) throws DaoException;
    int findPharmaciesNumber() throws DaoException ;
    int updateNumber(long id, int number) throws DaoException;
    int updateCity(long id, String city) throws DaoException;
    int updateStreet(long id, String street) throws DaoException;
    int updateHouse(long id, String house) throws DaoException;
    int updateBlock(long id, int block) throws DaoException;

}

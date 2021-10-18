package by.epam.onlinepharmacy.model.dao;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Pharmacy dao.
 */
public interface PharmacyDao {
    /**
     * Find list pharmacies list.
     *
     * @param startingPharmacy the starting pharmacy
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Pharmacy> findListPharmacies(int startingPharmacy) throws DaoException;

    /**
     * Find pharmacies by city list.
     *
     * @param city the city
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Pharmacy> findPharmaciesByCity(String city) throws DaoException;

    /**
     * Create pharmacy boolean.
     *
     * @param pharmacy the pharmacy
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createPharmacy(Pharmacy pharmacy) throws DaoException;

    /**
     * Find pharmacy by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Pharmacy> findPharmacyById(long id) throws DaoException;

    /**
     * Find pharmacies number int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int findPharmaciesNumber() throws DaoException ;

    /**
     * Update number int.
     *
     * @param id     the id
     * @param number the number
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateNumber(long id, int number) throws DaoException;

    /**
     * Update city int.
     *
     * @param id   the id
     * @param city the city
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateCity(long id, String city) throws DaoException;

    /**
     * Update street int.
     *
     * @param id     the id
     * @param street the street
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateStreet(long id, String street) throws DaoException;

    /**
     * Update house int.
     *
     * @param id    the id
     * @param house the house
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateHouse(long id, String house) throws DaoException;

    /**
     * Update block int.
     *
     * @param id    the id
     * @param block the block
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateBlock(long id, int block) throws DaoException;

}

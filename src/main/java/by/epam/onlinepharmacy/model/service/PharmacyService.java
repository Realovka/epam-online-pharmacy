package by.epam.onlinepharmacy.model.service;

import by.epam.onlinepharmacy.dto.PharmacyDto;
import by.epam.onlinepharmacy.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface Pharmacy service.
 */
public interface PharmacyService {
    /**
     * Find list pharmacies list.
     *
     * @param startingPharmacy the starting pharmacy
     * @return the list
     * @throws ServiceException the service exception
     */
    List<PharmacyDto> findListPharmacies(int startingPharmacy) throws ServiceException;

    /**
     * Find list pharmacies by city list.
     *
     * @param city the city
     * @return the list
     * @throws ServiceException the service exception
     */
    List<PharmacyDto> findListPharmaciesByCity(String city) throws ServiceException;

    /**
     * Create pharmacy list.
     *
     * @param number the number
     * @param city   the city
     * @param street the street
     * @param house  the house
     * @param block  the block
     * @return the list
     * @throws ServiceException the service exception
     */
    List<PharmacyDto> createPharmacy(String number, String city, String street, String house, String block) throws ServiceException;

    /**
     * Find current page int.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int findCurrentPage() throws ServiceException;

    /**
     * Is form valid map.
     *
     * @param number the number
     * @param city   the city
     * @param street the street
     * @param house  the house
     * @param block  the block
     * @return the map
     */
    Map<String, String> isFormValid(String number, String city, String street, String house, String block);

    /**
     * Find pharmacy by id pharmacy dto.
     *
     * @param id the id
     * @return the pharmacy dto
     * @throws ServiceException the service exception
     */
    PharmacyDto findPharmacyById(long id) throws ServiceException;

    /**
     * Update number.
     *
     * @param id     the id
     * @param number the number
     * @throws ServiceException the service exception
     */
    void updateNumber(long id, String number) throws ServiceException;

    /**
     * Update city.
     *
     * @param id   the id
     * @param city the city
     * @throws ServiceException the service exception
     */
    void updateCity(long id, String city) throws ServiceException;

    /**
     * Update street.
     *
     * @param id     the id
     * @param street the street
     * @throws ServiceException the service exception
     */
    void updateStreet(long id, String street) throws ServiceException;

    /**
     * Update house.
     *
     * @param id    the id
     * @param house the house
     * @throws ServiceException the service exception
     */
    void updateHouse(long id, String house) throws ServiceException;

    /**
     * Update block.
     *
     * @param id    the id
     * @param block the block
     * @throws ServiceException the service exception
     */
    void updateBlock(long id, String block) throws ServiceException;
}

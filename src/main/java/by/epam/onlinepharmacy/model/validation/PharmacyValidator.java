package by.epam.onlinepharmacy.model.validation;

import java.util.Map;

/**
 * The interface Pharmacy validator.
 */
public interface PharmacyValidator {
    /**
     * Is valid form boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean isValidForm(Map<String, String> formData);

    /**
     * Is valid number boolean.
     *
     * @param number the number
     * @return the boolean
     */
    boolean isValidNumber(String number);

    /**
     * Is valid city boolean.
     *
     * @param city the city
     * @return the boolean
     */
    boolean isValidCity(String city);

    /**
     * Is valid street boolean.
     *
     * @param street the street
     * @return the boolean
     */
    boolean isValidStreet(String street);

    /**
     * Is valid house boolean.
     *
     * @param house the house
     * @return the boolean
     */
    boolean isValidHouse(String house);

    /**
     * Is valid block boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean isValidBlock(Map<String, String> formData);

    /**
     * Is valid new block boolean.
     *
     * @param block the block
     * @return the boolean
     */
    boolean isValidNewBlock(String block);
}

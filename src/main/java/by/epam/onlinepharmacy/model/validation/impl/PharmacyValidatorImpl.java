package by.epam.onlinepharmacy.model.validation.impl;

import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.model.validation.PharmacyValidator;

import java.util.Map;

public class PharmacyValidatorImpl implements PharmacyValidator {

    private static final int MIN_FOR_BLOCK_AND_HOUSE = 0;
    private static final int MAX_SYMBOLS_FOR_CITY_AND_STREET = 70;
    private static final int MAX_SYMBOLS_FOR_HOUSE = 20;
    private static final String EMPTY_STRING = "";
    private static final String ZERO_STRING = "0";

    private static PharmacyValidatorImpl instance = new PharmacyValidatorImpl();

    private PharmacyValidatorImpl() {
    }

    public static PharmacyValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean isValidForm(Map<String, String> formData) {
        if (!isValidNumber(formData.get(RequestParameter.NUMBER))) {
            formData.put(RequestParameter.NUMBER, EMPTY_STRING);
        }
        if(!isValidCityOrStreet(formData.get(RequestParameter.CITY))) {
            formData.put(RequestParameter.CITY, EMPTY_STRING);
        }
        if (!isValidCityOrStreet(formData.get(RequestParameter.STREET))) {
            formData.put(RequestParameter.STREET, EMPTY_STRING);
        }
        if (!isValidHouse(formData.get(RequestParameter.HOUSE))) {
            formData.put(RequestParameter.HOUSE, EMPTY_STRING);
        }
        if (!isValidBlock(formData)) {
            formData.put(RequestParameter.BLOCK, EMPTY_STRING);
        }
        return !formData.containsValue(EMPTY_STRING);
    }

    @Override
    public boolean isValidNumber(String number) {
        int pharmacyNumber;
        try {
            pharmacyNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return pharmacyNumber > MIN_FOR_BLOCK_AND_HOUSE;
    }

    @Override
    public boolean isValidCityOrStreet(String parameter) {
        return !parameter.isBlank() && parameter.length() <= MAX_SYMBOLS_FOR_CITY_AND_STREET;
    }

    @Override
    public boolean isValidHouse(String house) {
        if(house.equals(ZERO_STRING)) {
            return false;
        }
        return !house.isBlank() && house.length() <= MAX_SYMBOLS_FOR_HOUSE;
    }

    @Override
    public boolean isValidBlock(Map<String, String> formData) {
        String block = formData.get(RequestParameter.BLOCK);
        if (block.isBlank()) {
            formData.put(RequestParameter.BLOCK, ZERO_STRING);
            return true;
        }
        int pharmacyBlock;
        try {
           pharmacyBlock = Integer.parseInt(block);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return pharmacyBlock >= 0;
    }

    @Override
    public boolean isValidNewBlock(String block) {
        int newBlock;
        if(block.isBlank()) {
            return true;
        }
        try {
            newBlock = Integer.parseInt(block);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return newBlock >= MIN_FOR_BLOCK_AND_HOUSE;
    }

}

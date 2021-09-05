package by.epam.onlinepharmacy.validation;

public class PharmacyValidator {

    private static final int MIN_FOR_NUMBER = 1;
    private static final int MAX_SYMBOLS_FOR_CITY_AND_STREET = 70;
    private static final int MAX_SYMBOLS_FOR_HOUSE = 20;

    public static boolean isValidNumber(String number) {
        int pharmacyNumber;
        try {
            pharmacyNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        if (pharmacyNumber < MIN_FOR_NUMBER) {
            return false;
        }
        return true;
    }

    public static boolean isValidStringParameters(String city, String street, String house) {
        if (city.isBlank() || street.isBlank() || house.isBlank()
                || city.length() > MAX_SYMBOLS_FOR_CITY_AND_STREET || street.length() > MAX_SYMBOLS_FOR_CITY_AND_STREET
                || house.length() > MAX_SYMBOLS_FOR_HOUSE) {
            return false;
        }
        return true;
    }

    public static boolean isValidBlock(String block) {
        if (block.isBlank()) {
            return true;
        }
        try {
            Integer.parseInt(block);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}

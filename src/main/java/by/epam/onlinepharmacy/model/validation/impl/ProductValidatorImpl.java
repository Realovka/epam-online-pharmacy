package by.epam.onlinepharmacy.model.validation.impl;

import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.model.validation.ProductValidator;

import java.math.BigInteger;
import java.util.Map;
import java.util.regex.Matcher;

public class ProductValidatorImpl implements ProductValidator {
    private static final int MAX_SYMBOLS_FOR_NAME_AND_GROUP = 45;
    private static double MIN_PRICE = 0.0;
    private static BigInteger MAX_SYMBOLS_FOR_INSTRUCTION = new BigInteger("4294967295");
    private static final String EMPTY_STRING = "";


    private ProductValidatorImpl() {
    }

    private static ProductValidatorImpl instance = new ProductValidatorImpl();

    public static ProductValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean isValidForm(Map<String, String> formData) {
        if (!isValidNameOrGroup(formData.get(RequestParameter.NAME))) {
            formData.put(RequestParameter.NAME, EMPTY_STRING);
        }
        if (!isValidNameOrGroup(formData.get(RequestParameter.GROUP))) {
            formData.put(RequestParameter.GROUP, EMPTY_STRING);
        }
        if (!isValidPrice(formData.get(RequestParameter.PRICE))) {
            formData.put(RequestParameter.PRICE, EMPTY_STRING);
        }
        if (!isValidInstruction(formData.get(RequestParameter.INSTRUCTION))) {
            formData.put(RequestParameter.INSTRUCTION, EMPTY_STRING);
        }
        return !formData.containsValue(EMPTY_STRING);
    }

    @Override
    public boolean isValidNameOrGroup(String parameter) {
        return !parameter.isBlank() && parameter.length() <= MAX_SYMBOLS_FOR_NAME_AND_GROUP;
    }

    @Override
    public boolean isValidPrice(String price) {
        double productPrice;
        try {
            productPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return !(productPrice < MIN_PRICE);
    }

    @Override
    public boolean isValidInstruction(String instruction) {
        if(instruction == null) {
            return false;
        }
        BigInteger instructionLength = new BigInteger(String.valueOf(instruction.length()));
        BigInteger result = instructionLength.max(MAX_SYMBOLS_FOR_INSTRUCTION);
        return result.equals(MAX_SYMBOLS_FOR_INSTRUCTION);
    }
}




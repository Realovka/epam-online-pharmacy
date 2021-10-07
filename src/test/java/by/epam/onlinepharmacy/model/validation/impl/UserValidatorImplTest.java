package by.epam.onlinepharmacy.model.validation.impl;

import by.epam.onlinepharmacy.model.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorImplTest {
    UserValidator userValidator;
    String correctParameter;
    String emptyParameter;
    String longParameter;
    String correctEmail;
    String incorrectEmail;
    String correctTelephone;
    String incorrectTelephone;
    Map<String, String> data;

    @BeforeEach
    public void setUp() {
        userValidator = UserValidatorImpl.getInstance();
        correctParameter = "parameter";
        emptyParameter = "\s";
        correctEmail = "email@mail.ru";
        incorrectEmail = "email@";
        correctTelephone = "+375291112233";
        incorrectTelephone = "+37529111223";
        data = new LinkedHashMap<>();
        data.put("login", "login");
        data.put("password", "password");
        data.put("firstName", "firsName");
        data.put("lastName", "lastName");
        data.put("email", correctEmail);
        data.put("telephone", correctTelephone);
        longParameter = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                qui officia deserunt mollit anim id est laborum.
                """;
    }

    @Test
    public void isValidFormTest() {
       boolean result = userValidator.isValidForm(data);
       assertTrue(result);
    }

    @Test
    public void isValidFormFalseEmptyLoginTest() {
        data.put("login", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLoginTest() {
        data.put("login", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseEmptyPasswordTest() {
        data.put("password", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseEmptyFirstNameTest() {
        data.put("firstName", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseEmptyLastNameTest() {
        data.put("lastName", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }


    @Test
    public void isValidStringParameterTest() {
        boolean result = userValidator.isValidStringParameter(correctParameter);
        assertTrue(result);
    }

    @Test
    public void isValidFormFalseEmptyEmailTest() {
        data.put("email", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseEmptyTelephoneTest() {
        data.put("telephone", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLongLoginTest() {
        data.put("login", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLongPasswordTest() {
        data.put("password", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLongFirstNameTest() {
        data.put("firstName", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLongLastNameTest() {
        data.put("lastName", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLongEmailTest() {
        data.put("email", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLongTelephoneTest() {
        data.put("telephone", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseIncorrectEmailTest() {
        data.put("email", incorrectEmail);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseIncorrectTelephoneTest() {
        data.put("telephone", incorrectTelephone);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidFormFalseLongEmailIncorrectTelephoneTest() {
        data.put("email", longParameter);
        data.put("telephone", incorrectTelephone);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void isValidStringParameterFalseParameterIsEmptyTest() {
        boolean result = userValidator.isValidStringParameter(emptyParameter);
        assertFalse(result);
    }

    @Test
    public void isValidStringParameterFalseParameterIsLongTest() {
        boolean result = userValidator.isValidStringParameter(longParameter);
        assertFalse(result);
    }

    @Test
    public void isValidEmailRegistrationUserTest() {
        boolean result = userValidator.isValidEmailRegistrationUser(correctEmail);
        assertTrue(result);
    }

    @Test
    public void isValidEmailRegistrationUserFalseParameterIsEmptyTest() {
        boolean result = userValidator.isValidEmailRegistrationUser(emptyParameter);
        assertFalse(result);
    }

    @Test
    public void isValidEmailRegistrationUserFalseParameterIsLongTest() {
        boolean result = userValidator.isValidEmailRegistrationUser(longParameter);
        assertFalse(result);
    }

    @Test
    public void isValidEmailRegistrationUserParameterFalseIsIncorrectTest() {
        boolean result = userValidator.isValidEmailRegistrationUser(incorrectEmail);
        assertFalse(result);
    }

    @Test
    public void isValidTelephoneRegistrationUserTest() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(correctTelephone);
        assertTrue(result);
    }

    @Test
    public void isValidTelephoneRegistrationUserFalseParameterIsEmptyTest() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(emptyParameter);
        assertFalse(result);
    }

    @Test
    public void isValidTelephoneRegistrationUserFalseParameterIsLongTest() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(longParameter);
        assertFalse(result);
    }

    @Test
    public void isValidTelephoneRegistrationUserFalseParameterIsIncorrectTest() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(incorrectTelephone);
        assertFalse(result);
    }
}

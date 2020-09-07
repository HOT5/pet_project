package com.epam.rd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

    private static final Logger logger = LoggerFactory.getLogger(Validator.class);

    private static final String loginReg = "^[a-z0-9]{3,20}$";
    private static final String emailReg = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String pass = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,20})";
    private static final String firstname = "([A-Z][a-zA-Z]+)|([А-Я][а-я]+)";
    private static final String lastname = "([A-Z][a-zA-Z]+)|([А-Я][а-я]+)";


    private Validator validator = null;

    private Validator() {
    }

    public static Validator getInstance() {
        return new Validator();
    }

    public boolean isLoginValid(String login) {
        if (login == null || login.isEmpty()) {
            logger.info("User login is empty");
            return false;
        } else {
            logger.info(String.format("User login is valid: %b", login.matches(loginReg)));
            return login.matches(loginReg);
        }
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            logger.info("User email is empty");
            return false;
        } else {
            logger.info(String.format("User email is valid: %b", email.matches(emailReg)));
            return email.matches(emailReg);
        }
    }

    public boolean isPasswordValid(String password) {
        if (password == null || password.isEmpty()) {
            logger.info("User password is empty");
            return false;
        } else {
            logger.info(String.format("User password is valid: %b", password.matches(pass)));
            return password.matches(pass);
        }
    }

    public boolean isFirstNameValid(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            logger.info("User first name is empty");
            return false;
        } else {
            logger.info(String.format("User first name is valid: %b", firstName.matches(firstname)));
            return firstName.matches(firstname);
        }
    }

    public boolean isLastNameValid(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            logger.info("User last name is empty");
            return false;
        } else {
            logger.info(String.format("User last name is valid: %b", lastName.matches(lastname)));
            return lastName.matches(lastname);
        }
    }
}

package by.epam.onlinepharmacy.model.util.impl;

import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Password encoder.
 */
public class PasswordEncoderImpl implements PasswordEncoder {
    private static final Logger logger = LogManager.getLogger();
    private static PasswordEncoderImpl instance = new PasswordEncoderImpl();

    private PasswordEncoderImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PasswordEncoderImpl getInstance() {
        return instance;
    }

    public String createPasswordEncoded(String password) throws ServiceException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.ERROR, "No such algorithm  ", ex);
            throw new ServiceException("No such algorithm  ", ex);
        }
        byte[] loginHash = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte item : loginHash) {
            builder.append(String.format("%02X", item));
        }
        return builder.toString();
    }
}

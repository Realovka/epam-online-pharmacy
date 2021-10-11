package by.epam.onlinepharmacy.model.verification.impl;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.model.verification.EmailSending;
import by.epam.onlinepharmacy.model.verification.MailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class EmailSendingImpl implements EmailSending {
    private static final Logger logger = LogManager.getLogger();
    private static EmailSendingImpl instance = new EmailSendingImpl();

    private EmailSendingImpl() {
    }

    public static EmailSendingImpl getInstance() {
        return instance;
    }

    @Override
    public void sendEmailVerifyCustomer(User user, String codeOrOrderNumber, String header, String message) {
        Properties properties = getProp();
        if (!user.getEmail().isEmpty()) {
            String sendMessage = String.format(
                    message,
                    user.getFirstName(),
                    user.getLastName(),
                    codeOrOrderNumber
            );
            MailSender sender = new MailSender(user.getEmail(), header, sendMessage, properties);
            sender.send();
        }
    }

    @Override
    public void sendEmailPrepareOrder(User user, String code, Pharmacy pharmacy, String header, String message) {
        Properties properties = getProp();
        if (!user.getEmail().isEmpty()) {
            String sendMessage = String.format(
                    message,
                    user.getFirstName(),
                    user.getLastName(),
                    code, pharmacy.getNumber(), pharmacy.getCity(), pharmacy.getStreet(), pharmacy.getHouse()
            );
            MailSender sender = new MailSender(user.getEmail(), header, sendMessage, properties);
            sender.send();
        }

    }
    private Properties getProp() {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("prop/mail.properties"));
        } catch (IOException e) {
            logger.log(Level.ERROR, "IOException in method getProp read properties ", e);
        }
        return properties;
    }
}
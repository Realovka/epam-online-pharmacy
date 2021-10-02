package by.epam.onlinepharmacy.model.verification.impl;

import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.model.verification.EmailSending;
import by.epam.onlinepharmacy.model.verification.MailSender;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class EmailSendingImpl implements EmailSending {
    private static EmailSendingImpl instance = new EmailSendingImpl();

    private EmailSendingImpl() {
    }

    public static EmailSendingImpl getInstance() {
        return instance;
    }
    @Override
    public void sendEmail(User user, String codeOrOrderNumber, String header, String message) {

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("prop/mail.properties"));
            //TODO
            properties.load(new FileReader("E://epam//onlinepharmacy//src//main//resources//prop//mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


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
}

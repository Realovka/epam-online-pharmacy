package by.epam.onlinepharmacy.model.verification;

import by.epam.onlinepharmacy.entity.User;

public interface EmailSending {
    void sendEmail(User user, String codeOrOrderNumber, String header, String message);
}

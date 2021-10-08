package by.epam.onlinepharmacy.model.verification;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.entity.User;

public interface EmailSending {
    void sendEmailVerifyCustomer(User user, String code, String header, String message);
    void sendEmailPrepareOrder(User user, String code, Pharmacy pharmacy, String header, String message);
}

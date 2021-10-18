package by.epam.onlinepharmacy.model.verification;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.entity.User;

/**
 * The interface Email sending.
 */
public interface EmailSending {
    /**
     * Send email verify customer.
     *
     * @param user    the user
     * @param code    the code
     * @param header  the header
     * @param message the message
     */
    void sendEmailVerifyCustomer(User user, String code, String header, String message);

    /**
     * Send email prepare order.
     *
     * @param user     the user
     * @param code     the code
     * @param pharmacy the pharmacy
     * @param header   the header
     * @param message  the message
     */
    void sendEmailPrepareOrder(User user, String code, Pharmacy pharmacy, String header, String message);
}

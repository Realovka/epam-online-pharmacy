package by.epam.onlinepharmacy.entity;

import java.time.LocalDateTime;

public class Order {
    private long orderId;
    private LocalDateTime dataStarting;
    private LocalDateTime dataEnding;
    private StatusOrder statusOrder;
    private long pharmacyId;

}

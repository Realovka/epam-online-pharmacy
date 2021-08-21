package by.epam.onlinepharmacy.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private long orderId;
    private LocalDateTime dataStarting;
    private LocalDateTime dataEnding;
    private StatusOrder statusOrder;
    private long pharmacyId;

    public Order() {
    }

    public Order(long orderId, LocalDateTime dataStarting, LocalDateTime dataEnding, StatusOrder statusOrder, long pharmacyId) {
        this.orderId = orderId;
        this.dataStarting = dataStarting;
        this.dataEnding = dataEnding;
        this.statusOrder = statusOrder;
        this.pharmacyId = pharmacyId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDataStarting() {
        return dataStarting;
    }

    public void setDataStarting(LocalDateTime dataStarting) {
        this.dataStarting = dataStarting;
    }

    public LocalDateTime getDataEnding() {
        return dataEnding;
    }

    public void setDataEnding(LocalDateTime dataEnding) {
        this.dataEnding = dataEnding;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && pharmacyId == order.pharmacyId && Objects.equals(dataStarting, order.dataStarting)
                && Objects.equals(dataEnding, order.dataEnding) && statusOrder == order.statusOrder;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result *= 31 + (dataStarting != null ? dataStarting.hashCode() : 0);
        result *= 31 + (dataEnding != null ? dataEnding.hashCode() : 0);
        result *= 31 + (statusOrder != null ? statusOrder.hashCode() : 0);
        result *= (int) (pharmacyId ^ (pharmacyId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order{");
        builder.append("orderId=").append(orderId);
        builder.append(", dataStarting=").append(dataStarting);
        builder.append(", dataEnding=").append(dataEnding);
        builder.append(", statusOrder=").append(statusOrder);
        builder.append(", pharmacyId=").append(pharmacyId);
        builder.append('}');
        return builder.toString();
    }
}

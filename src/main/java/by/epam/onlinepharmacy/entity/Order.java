package by.epam.onlinepharmacy.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private long orderId;
    private Timestamp dataStarting;
    private Timestamp dataEnding;
    private StatusOrder statusOrder;
    private Pharmacy pharmacy;

    public Order() {
    }

    public Order(long orderId, Timestamp dataStarting, Timestamp dataEnding, StatusOrder statusOrder, Pharmacy pharmacy) {
        this.orderId = orderId;
        this.dataStarting = dataStarting;
        this.dataEnding = dataEnding;
        this.statusOrder = statusOrder;
        this.pharmacy = pharmacy;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getDataStarting() {
        return dataStarting;
    }

    public void setDataStarting(Timestamp dataStarting) {
        this.dataStarting = dataStarting;
    }

    public Timestamp getDataEnding() {
        return dataEnding;
    }

    public void setDataEnding(Timestamp dataEnding) {
        this.dataEnding = dataEnding;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && pharmacy == order.pharmacy && Objects.equals(dataStarting, order.dataStarting)
                && Objects.equals(dataEnding, order.dataEnding) && statusOrder == order.statusOrder;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result *= 31 + (dataStarting != null ? dataStarting.hashCode() : 0);
        result *= 31 + (dataEnding != null ? dataEnding.hashCode() : 0);
        result *= 31 + (statusOrder != null ? statusOrder.hashCode() : 0);
        result *= 31 + (pharmacy != null ? pharmacy.hashCode() : 0);
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
        builder.append(", pharmacy=").append(pharmacy);
        builder.append("}");
        return builder.toString();
    }

    public static class Builder {
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }

        public Order.Builder setOrderId(long orderId) {
            newOrder.orderId = orderId;
            return this;
        }

        public Order.Builder setDataStarting(Timestamp dataStarting) {
            newOrder.dataStarting = dataStarting;
            return this;
        }

        public Order.Builder setDataEnding(Timestamp dataEnding) {
            newOrder.dataEnding = dataEnding;
            return this;
        }


        public Order.Builder setStatusOrder(StatusOrder statusOrder) {
            newOrder.statusOrder = statusOrder;
            return this;
        }

        public Order.Builder setPharmacy(Pharmacy pharmacy) {
            newOrder.pharmacy = pharmacy;
            return this;
        }

        public Order build() {
            return newOrder;
        }
    }
}

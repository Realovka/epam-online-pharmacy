package by.epam.onlinepharmacy.entity;

public class StatusOrder {
    private int statusOrderId;
    private String statusOrder;

    public StatusOrder(int statusOrderId, String statusOrder) {
        this.statusOrderId = statusOrderId;
        this.statusOrder = statusOrder;
    }

    public int getStatusOrderId() {
        return statusOrderId;
    }

    public void setStatusOrderId(int statusOrderId) {
        this.statusOrderId = statusOrderId;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}

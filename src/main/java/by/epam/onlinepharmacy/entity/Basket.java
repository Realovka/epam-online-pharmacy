package by.epam.onlinepharmacy.entity;

public class Basket {
    private long basketId;
    private long userId;
    private long productId;
    private long orderId;
    private int quantity;

    public Basket() {
    }

    public Basket(long basketId, long userId, long productId, long orderId, int quantity) {
        this.basketId = basketId;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return basketId == basket.basketId && userId == basket.userId && productId == basket.productId
                && orderId == basket.orderId && quantity == basket.quantity;
    }

    @Override
    public int hashCode() {
        int result = (int) (basketId ^ (basketId >>> 32));
        result *= (int) (userId ^ (userId >>> 32));
        result *= (int) (productId ^ (productId >>> 32));
        result *= (int) (orderId ^ (orderId >>> 32));
        result *= quantity;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Basket{");
        builder.append("basketId=").append(basketId);
        builder.append(", userId=").append(userId);
        builder.append(", productId=").append(productId);
        builder.append(", orderId=").append(orderId);
        builder.append(", quantity=").append(quantity);
        builder.append('}');
        return builder.toString();
    }
}

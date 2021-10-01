package by.epam.onlinepharmacy.entity;

public class Basket {
    private long basketId;
    private User user;
    private Product product;
    private Order order;
    private int quantity;

    public Basket() {
    }

    public Basket(long basketId, User user, Product product, Order order, int quantity) {
        this.basketId = basketId;
        this.user = user;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
        return basketId == basket.basketId && user == basket.user && product == basket.product
                && order == basket.order && quantity == basket.quantity;
    }

    @Override
    public int hashCode() {
        int result = (int) (basketId ^ (basketId >>> 32));
        result *= 31 + (user != null ? user.hashCode() : 0);
        result *= 31 + (product != null ? product.hashCode() : 0);
        result *= 31 + (order != null ? order.hashCode() : 0);
        result *= quantity;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Basket{");
        builder.append("basketId=").append(basketId);
        builder.append(", user=").append(user);
        builder.append(", product=").append(product);
        builder.append(", order=").append(order);
        builder.append(", quantity=").append(quantity);
        builder.append("}");
        return builder.toString();
    }

    public static class Builder {
        private Basket newBasket;

        public Builder() {
            newBasket = new Basket();
        }

        public Basket.Builder setBasketId(long basketId) {
            newBasket.basketId = basketId;
            return this;
        }

        public Basket.Builder setUser(User user) {
            newBasket.user = user;
            return this;
        }

        public Basket.Builder setProduct(Product product) {
            newBasket.product = product;
            return this;
        }


        public Basket.Builder setOrder(Order order) {
            newBasket.order = order;
            return this;
        }

        public Basket.Builder setQuantity(int quantity) {
            newBasket.quantity = quantity;
            return this;
        }

        public Basket build() {
            return newBasket;
        }
    }
}

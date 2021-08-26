package by.epam.onlinepharmacy.entity;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Objects;

public class Product {
    private long productId;
    private String name;
    private String group;
    public BigDecimal price;
    private boolean recipe;
    private Blob picture;
    private String instruction;

    public Product() {
    }

    public Product(long productId, String name, String group, BigDecimal price, boolean recipe, Blob picture, String instruction) {
        this.productId = productId;
        this.name = name;
        this.group = group;
        this.price = price;
        this.recipe = recipe;
        this.picture = picture;
        this.instruction = instruction;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result *= 31 + (name != null ? name.hashCode() : 0);
        result *= 31 + (group != null ? group.hashCode() : 0);
        result *= 31 + (price != null ? price.hashCode() : 0);
        result *= recipe ? 1 : 0;
        result *= 31 + (picture != null ? picture.hashCode() : 0);
        result *= 31 + (instruction != null ? instruction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product{");
        builder.append("productId=").append(productId);
        builder.append(", name='").append(name);
        builder.append(", group='").append(group);
        builder.append(", price=").append(price);
        builder.append(", recipe=").append(recipe);
        builder.append(", picture='").append(picture);
        builder.append(", instruction='").append(instruction);
        builder.append('}');
        return builder.toString();
    }
}

package by.epam.onlinepharmacy.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private long productId;
    private String name;
    private String nonProprietaryName;
    private String dose;
    private String plant;
    private String group;
    public BigDecimal price;
    private boolean recipe;
    private String pathToPicture;
    private String instruction;

    public Product() {
    }

    public Product(long productId, String name, String nonProprietaryName,
                   String dose, String plant, String group, BigDecimal price,
                   boolean recipe, String pathToPicture, String instruction) {
        this.productId = productId;
        this.name = name;
        this.nonProprietaryName = nonProprietaryName;
        this.dose = dose;
        this.plant = plant;
        this.group = group;
        this.price = price;
        this.recipe = recipe;
        this.pathToPicture = pathToPicture;
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

    public String getNonProprietaryName() {
        return nonProprietaryName;
    }

    public void setNonProprietaryName(String nonProprietaryName) {
        this.nonProprietaryName = nonProprietaryName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
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

    public String getPathToPicture() {
        return pathToPicture;
    }

    public void setPathToPicture(String pathToPicture) {
        this.pathToPicture = pathToPicture;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && recipe == product.recipe
                && Objects.equals(name, product.name)
                && Objects.equals(nonProprietaryName, product.nonProprietaryName)
                && Objects.equals(dose, product.dose)
                && Objects.equals(plant, product.plant)
                && Objects.equals(group, product.group)
                && Objects.equals(price, product.price)
                && Objects.equals(pathToPicture, product.pathToPicture)
                && Objects.equals(instruction, product.instruction);
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result *= 31 + (name != null ? name.hashCode() : 0);
        result *= 31 + (nonProprietaryName != null ? nonProprietaryName.hashCode() : 0);
        result *= 31 + (dose != null ? dose.hashCode() : 0);
        result *= 31 + (plant != null ? plant.hashCode() : 0);
        result *= 31 + (group != null ? group.hashCode() : 0);
        result *= 31 + (price != null ? price.hashCode() : 0);
        result *= recipe ? 1 : 0;
        result *= 31 + (pathToPicture != null ? pathToPicture.hashCode() : 0);
        result *= 31 + (instruction != null ? instruction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product{");
        builder.append("productId=").append(productId);
        builder.append(", name=").append(name);
        builder.append(", nonProprietaryName=").append(nonProprietaryName);
        builder.append(", dose=").append(dose);
        builder.append(", plant=").append(plant);
        builder.append(", group=").append(group);
        builder.append(", price=").append(price);
        builder.append(", recipe=").append(recipe);
        builder.append(", picture=").append(pathToPicture);
        builder.append(", instruction=").append(instruction);
        builder.append("}");
        return builder.toString();
    }

    public static class Builder {
        private Product newProduct;

        public Builder() {
            newProduct = new Product();
        }

        public Product.Builder setProductId(long productId) {
            newProduct.productId = productId;
            return this;
        }

        public Product.Builder setName(String name) {
            newProduct.name = name;
            return this;
        }

        public Product.Builder setNonProprietaryName(String nonProprietaryName) {
            newProduct.nonProprietaryName = nonProprietaryName;
            return this;
        }

        public Product.Builder setDose(String dose) {
            newProduct.dose = dose;
            return this;
        }

        public Product.Builder setPlant(String plant) {
            newProduct.plant = plant;
            return this;
        }

        public Product.Builder setGroup(String group) {
            newProduct.group = group;
            return this;
        }


        public Product.Builder setPrice(BigDecimal price) {
            newProduct.price = price;
            return this;
        }

        public Product.Builder isRecipe(boolean recipe) {
            newProduct.recipe = recipe;
            return this;
        }

        public Product.Builder setPicture(String pathToPicture) {
            newProduct.pathToPicture = pathToPicture;
            return this;
        }

        public Product.Builder setInstruction(String instruction) {
            newProduct.instruction = instruction;
            return this;
        }

        public Product build() {
            return newProduct;
        }
    }
}

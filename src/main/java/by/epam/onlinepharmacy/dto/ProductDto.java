package by.epam.onlinepharmacy.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {
    private long productId;
    private String name;
    private String nonProprietaryName;
    private String dose;
    private String plant;
    private String group;
    private BigDecimal price;
    private String recipe;
    private String pathToPicture;
    private String instruction;

    public ProductDto() {
    }

    public ProductDto(long productId, String name, String nonProprietaryName, String dose,
                      String plant, String group, BigDecimal price, String recipe, String pathToPicture, String instruction) {
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

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
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
        ProductDto that = (ProductDto) o;
        return productId == that.productId &&
                Objects.equals(name, that.name)
                && Objects.equals(nonProprietaryName, that.nonProprietaryName)
                && Objects.equals(dose, that.dose)
                && Objects.equals(plant, that.plant)
                && Objects.equals(group, that.group)
                && Objects.equals(price, that.price)
                && Objects.equals(recipe, that.recipe)
                && Objects.equals(pathToPicture, that.pathToPicture)
                && Objects.equals(instruction, that.instruction);
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result *= 31 + (name != null ? name.hashCode() : 0);
        result *= 31 + (nonProprietaryName != null ? nonProprietaryName.hashCode() : 0);
        result *= 31 + (dose != null ? dose.hashCode() : 0);
        result *= 31 + (group != null ? group.hashCode() : 0);
        result *= 31 + (plant != null ? plant.hashCode() : 0);
        result *= 31 + (price != null ? price.hashCode() : 0);
        result *= 31 + (recipe != null ? recipe.hashCode() : 0);
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
        private ProductDto newProduct;

        public Builder() {
            newProduct = new ProductDto();
        }

        public ProductDto.Builder setProductId(long productId) {
            newProduct.productId = productId;
            return this;
        }

        public ProductDto.Builder setName(String name) {
            newProduct.name = name;
            return this;
        }

        public ProductDto.Builder setNonProprietaryName(String nonProprietaryName) {
            newProduct.nonProprietaryName = nonProprietaryName;
            return this;
        }

        public ProductDto.Builder setDose(String dose) {
            newProduct.dose = dose;
            return this;
        }

        public ProductDto.Builder setPlant(String plant) {
            newProduct.plant = plant;
            return this;
        }

        public ProductDto.Builder setGroup(String group) {
            newProduct.group = group;
            return this;
        }


        public ProductDto.Builder setPrice(BigDecimal price) {
            newProduct.price = price;
            return this;
        }

        public ProductDto.Builder setRecipe(String recipe) {
            newProduct.recipe = recipe;
            return this;
        }

        public ProductDto.Builder setPicture(String pathToPicture) {
            newProduct.pathToPicture = pathToPicture;
            return this;
        }

        public ProductDto.Builder setInstruction(String instruction) {
            newProduct.instruction = instruction;
            return this;
        }

        public ProductDto build() {
            return newProduct;
        }
    }
}

package by.epam.onlinepharmacy.dto;

import by.epam.onlinepharmacy.entity.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {
    private long productId;
    private String name;
    private String group;
    public BigDecimal price;
    private String recipe;
    private String pathToPicture;
    private String instruction;

    public ProductDto() {
    }

    public ProductDto(long productId, String name, String group,
                      BigDecimal price, String recipe, String pathToPicture, String instruction) {
        this.productId = productId;
        this.name = name;
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
        ProductDto product = (ProductDto) o;
        return productId == product.productId && recipe.equals(product.recipe)
                && Objects.equals(name, product.name) && Objects.equals(group, product.group)
                && Objects.equals(price, product.price) && Objects.equals(pathToPicture, product.pathToPicture)
                && Objects.equals(instruction, product.instruction);
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result *= 31 + (name != null ? name.hashCode() : 0);
        result *= 31 + (group != null ? group.hashCode() : 0);
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
        builder.append(", name='").append(name);
        builder.append(", group='").append(group);
        builder.append(", price=").append(price);
        builder.append(", recipe=").append(recipe);
        builder.append(", picture='").append(pathToPicture);
        builder.append(", instruction='").append(instruction);
        builder.append('}');
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

package com.mercadolivre.desafiospring1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String name;
    private String category;
    private String brand;
    private Integer quantity;
    private BigDecimal price;
    private boolean freeShipping;
    private String prestige;

    public Product(Long productId, String name, String brand) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(name, product.name) && Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, brand);
    }
}
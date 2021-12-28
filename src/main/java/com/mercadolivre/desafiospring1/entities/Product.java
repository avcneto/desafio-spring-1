package com.mercadolivre.desafiospring1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

}
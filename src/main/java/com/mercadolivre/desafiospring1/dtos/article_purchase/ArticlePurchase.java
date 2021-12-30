package com.mercadolivre.desafiospring1.dtos.article_purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePurchase {
    private Long productId;
    private String name;
    private String brand;
    private Integer quantity;

    @Override
    public String toString() {
        return "name='" + name;

    }
}
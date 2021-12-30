package com.mercadolivre.desafiospring1.dtos;

import com.mercadolivre.desafiospring1.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long productId;
    private String name;
    private Integer quantity;

    public static ProductDTO convert(Product product){
        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }
}

package com.mercadolivre.desafiospring1.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {
    private Long id;
    private List<ProductDTO> articlesDTO = new ArrayList<>();

}
package com.mercadolivre.desafiospring1.entities;

import com.mercadolivre.desafiospring1.dtos.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    private Long id;
    private List<Product> article;
    private BigDecimal total;
}

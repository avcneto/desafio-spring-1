package com.mercadolivre.desafiospring1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    private Long id;
    private Article article;
    private BigDecimal total;
}

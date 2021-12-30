package com.mercadolivre.desafiospring1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    private List<Ticket> shoppingCart = new ArrayList<>();
    private BigDecimal total;
}

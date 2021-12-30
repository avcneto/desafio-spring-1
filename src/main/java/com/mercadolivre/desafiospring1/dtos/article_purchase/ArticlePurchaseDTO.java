package com.mercadolivre.desafiospring1.dtos.article_purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePurchaseDTO {
    List<ArticlePurchase> articlePurchases;

}


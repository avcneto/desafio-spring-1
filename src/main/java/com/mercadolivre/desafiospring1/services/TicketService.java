package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.dtos.article_purchase.ArticlePurchase;
import com.mercadolivre.desafiospring1.dtos.article_purchase.ArticlePurchaseDTO;
import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.entities.Ticket;
import com.mercadolivre.desafiospring1.exception.PurchaseException;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ProductService productService;

    public Ticket purchaseRequest(ArticlePurchaseDTO articlePurchase) {
        List<ArticlePurchase> productsNotExist = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Product> productList = productService.findAllProductsAvaliable();
        BigDecimal total = BigDecimal.ZERO;

        for (ArticlePurchase purchase : articlePurchase.getArticlePurchases()) {
            Product product = new Product(purchase.getProductId());
            if (productList.contains(product)) {
                Product prod = productList.get(productList.indexOf(product));
                if (prod.getQuantity() < purchase.getQuantity()) {
                    throw new PurchaseException("Product " + prod.getName() + " quantity unavailable. Available quantity: " + prod.getQuantity());
                }
                total = total.add(prod.getPrice().multiply(new BigDecimal(purchase.getQuantity())));
                products.add(prod);

            } else {
                productsNotExist.add(purchase);
            }
        }

        if (!productsNotExist.isEmpty()) {
            throw new PurchaseException("Products not found " + productsNotExist.toString());
        }

        return new Ticket(1L, products, total);
    }
}

package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.entities.Article;
import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.entities.Ticket;
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

    public Ticket purchaseRequest(Article article) {
        List<Product> productsNotExist = new ArrayList<>();
        List<Product> productList = productService.findAllProductsAvaliable();
        List<Product> purchaseProduts = article.getArticles();

        for (Product purchase : purchaseProduts) {
            if (productList.contains(purchase)) {
                Integer quantity = productList.get(productList.indexOf(purchase)).getQuantity();
                if (quantity < purchase.getQuantity()) {
                    //TODO
                    throw new InvalidRequestStateException();
                }

            } else {
                productsNotExist.add(purchase);
            }
        }

        if (!productsNotExist.isEmpty()) {
            //TODO
            throw new InvalidRequestStateException();
        }

        BigDecimal total = purchaseProduts.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Ticket(1L, new Article(purchaseProduts), total);
    }
}

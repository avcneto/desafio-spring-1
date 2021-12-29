package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.dtos.ArticleDTO;
import com.mercadolivre.desafiospring1.dtos.ProductDTO;
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

    public Ticket purchaseRequest(ArticleDTO article) {
        List<ProductDTO> productsNotExist = new ArrayList<>();
        List<Product> productList = productService.findAllProductsAvaliable();
        List<ProductDTO> purchaseProduts = article.getArticlesDTO();
        BigDecimal total = BigDecimal.ZERO;

        for (ProductDTO purchase : purchaseProduts) {
            if (productList.contains(purchase)) {
                Product prod = productList.get(productList.indexOf(purchase));
                total = total.add(prod.getPrice().multiply(new BigDecimal(purchase.getQuantity())));
                if (prod.getQuantity() < purchase.getQuantity()) {
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


        return new Ticket(1L, new ArticleDTO(purchaseProduts), total);
    }
}

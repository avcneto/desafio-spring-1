package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.dtos.article_purchase.ArticlePurchase;
import com.mercadolivre.desafiospring1.dtos.article_purchase.ArticlePurchaseDTO;
import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.entities.Ticket;
import com.mercadolivre.desafiospring1.exception.PurchaseException;
import com.mercadolivre.desafiospring1.exception.RepositoryException;
import com.mercadolivre.desafiospring1.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mercadolivre.desafiospring1.util.Constants.MESSAGE_ERROR_NOT_FOUND;
import static com.mercadolivre.desafiospring1.util.Constants.MESSAGE_ERROR_REPOSITORY_SAVE;

@Service
public class TicketService {
    @Autowired
    private ProductService productService;
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket purchaseRequest(ArticlePurchaseDTO articlePurchase) {
        BigDecimal total = BigDecimal.ZERO;
        List<Product> products = new ArrayList<>();
        List<ArticlePurchase> productsNotExist = new ArrayList<>();
        List<Product> productList = productService.findAllProductsAvaliable();

        for (ArticlePurchase purchase : articlePurchase.getArticlePurchases()) {
            Product product = new Product(purchase.getProductId(), purchase.getName(), purchase.getBrand());

            if (validateProducts(purchase, product, productList, productsNotExist)) {
                Product prod = productList.get(productList.indexOf(product));
                Product prodPurchase = createPurchase(purchase, prod);

                validateQuantity(prodPurchase, prod);
                total = total.add(calculatePrice(prodPurchase));
                products.add(prodPurchase);
            }
        }

        productNotExist(productsNotExist);
        Ticket ticket = new Ticket(new Random().nextInt(Integer.MAX_VALUE) + 2L, products, total);

        try {
            ticketRepository.saveTicket(ticket);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RepositoryException(MESSAGE_ERROR_REPOSITORY_SAVE);
        }

        return ticket;
    }

    public boolean validateProducts(ArticlePurchase purchase,
                                    Product product, List<Product> productList, List<ArticlePurchase> productsNotExist) {
        if (!productList.contains(product)) {
            productsNotExist.add(purchase);
            return false;
        }
        return true;
    }

    public BigDecimal calculatePrice(Product prodPurchase) {
        return prodPurchase.getPrice().multiply(new BigDecimal(prodPurchase.getQuantity()));
    }

    public void validateQuantity(Product purchase, Product product) {
        if (product.getQuantity() < purchase.getQuantity())
            throw new PurchaseException("Product "
                    + product.getName() +
                    " quantity unavailable. Available quantity: "
                    + product.getQuantity());
    }

    public void productNotExist(List<ArticlePurchase> productsNotExist) {
        if (!productsNotExist.isEmpty()) {
            throw new PurchaseException(MESSAGE_ERROR_NOT_FOUND + productsNotExist.toString());
        }
    }

    public Product createPurchase(ArticlePurchase purchase, Product prod) {

        return new Product(prod.getProductId(), prod.getName(), prod.getCategory(), prod.getBrand(),
                purchase.getQuantity(), prod.getPrice(), prod.isFreeShipping(), prod.getPrestige());

    }

}

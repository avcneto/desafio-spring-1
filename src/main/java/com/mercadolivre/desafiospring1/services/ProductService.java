package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.exception.RepositoryException;
import com.mercadolivre.desafiospring1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
   private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        List<Product> products = null;
        try {
            products = productRepository.findAllProducts();
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
        return products;
    }
}

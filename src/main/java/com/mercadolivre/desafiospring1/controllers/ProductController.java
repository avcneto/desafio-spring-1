package com.mercadolivre.desafiospring1.controllers;

import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/articles")
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> productList = productService.findAllProducts();
    return ResponseEntity.ok(productList);
    }
}

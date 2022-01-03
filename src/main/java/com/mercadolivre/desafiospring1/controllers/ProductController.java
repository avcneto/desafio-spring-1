package com.mercadolivre.desafiospring1.controllers;

import com.mercadolivre.desafiospring1.dtos.ArticleDTO;
import com.mercadolivre.desafiospring1.entities.Article;
import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/articles")
    public ResponseEntity<List<Product>> getProductByAnyQuery(@RequestParam Map<String, String> requestParams) {
        List<Product> productList = productService.getProductByAnyQuery(requestParams);
        return ResponseEntity.ok(productList);
    }

    @PostMapping(path = "/insert-articles-request")
    public ResponseEntity<ArticleDTO> saveProducts(@RequestBody Article article) {
        ArticleDTO articleDTO = productService.saveProducts(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleDTO);
    }
}
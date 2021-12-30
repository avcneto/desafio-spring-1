package com.mercadolivre.desafiospring1.controllers;

import com.mercadolivre.desafiospring1.entities.ShoppingCart;
import com.mercadolivre.desafiospring1.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public ResponseEntity<ShoppingCart> getShoppingCart() {
        return ResponseEntity.ok(shoppingCartService.getShoppingCart());
    }

}

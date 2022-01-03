package com.mercadolivre.desafiospring1.controllers;

import com.mercadolivre.desafiospring1.dtos.article_purchase.ArticlePurchaseDTO;
import com.mercadolivre.desafiospring1.entities.Ticket;
import com.mercadolivre.desafiospring1.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(path = "/purchase-request")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody ArticlePurchaseDTO articlePurchase, UriComponentsBuilder uriBuilder) {
        Ticket ticket = ticketService.purchaseRequest(articlePurchase);
        URI uri = uriBuilder
                .path("/purchase-request/{id}")
                .buildAndExpand(ticket.getId())
                .toUri();
        return ResponseEntity.created(uri).body(ticket);
    }
}

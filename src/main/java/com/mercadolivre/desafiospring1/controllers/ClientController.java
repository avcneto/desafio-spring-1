package com.mercadolivre.desafiospring1.controllers;

import com.mercadolivre.desafiospring1.entities.Client;
import com.mercadolivre.desafiospring1.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(path = "/add")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        clientService.createClient(client);
        return ResponseEntity.status(201).body(client);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Client>> findAll() {
        List<Client> listCli = clientService.findAll();
        return ResponseEntity.status(200).body(listCli);
    }

    @GetMapping(path = "/state")
    public ResponseEntity<List<Client>> findAllByState(@RequestParam String state) {
        List<Client> listCli = clientService.findAllByState(state);
        return ResponseEntity.status(200).body(listCli);
    }
}

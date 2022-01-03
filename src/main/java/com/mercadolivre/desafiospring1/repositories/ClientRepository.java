package com.mercadolivre.desafiospring1.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolivre.desafiospring1.entities.Client;
import com.mercadolivre.desafiospring1.exception.RepositoryException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClientRepository {

    private List<Client> client = new ArrayList<>();
    private final String PATH = "src/main/resources/json/clients.json";
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public List<Client> findAllClients() {
        File file = new File(PATH);

        try {
            FileInputStream is = new FileInputStream(file);
            client = Arrays.asList(objectMapper.readValue(is, Client[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }

    public void saveClient(Client client) {
        try {

            List<Client> listClients = new ArrayList<>(findAllClients());

            if (listClients.contains(client))
                throw new RepositoryException("Customer already exists in the database");

            listClients.add(client);
            objectMapper.writeValue(new File(PATH), listClients);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

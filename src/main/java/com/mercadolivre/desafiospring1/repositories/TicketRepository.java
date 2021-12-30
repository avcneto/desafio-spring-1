package com.mercadolivre.desafiospring1.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolivre.desafiospring1.entities.Ticket;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TicketRepository {

    private final String PATH = "src/main/resources/json/tickets.json";
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public List<Ticket> findAllTickets() throws IOException {
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        return Arrays.asList(objectMapper.readValue(is, Ticket[].class));
    }

    public void saveTicket(Ticket ticket) throws IOException {
        List<Ticket> listTickets = new ArrayList<>(findAllTickets());
        listTickets.add(ticket);
        objectMapper.writeValue(new File(PATH), listTickets);
    }
}

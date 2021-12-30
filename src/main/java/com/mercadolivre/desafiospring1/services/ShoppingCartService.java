package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.entities.ShoppingCart;
import com.mercadolivre.desafiospring1.entities.Ticket;
import com.mercadolivre.desafiospring1.exception.RepositoryException;
import com.mercadolivre.desafiospring1.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.mercadolivre.desafiospring1.util.Constants.MESSAGE_ERROR_REPOSITORY_FIND;

@Service
public class ShoppingCartService {

    @Autowired
    private TicketRepository ticketRepository;

    public BigDecimal getTotalTickets(){

            return getAllTickets().stream()
                    .map(Ticket::getTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ShoppingCart getShoppingCart(){

        return new ShoppingCart(getAllTickets(), getTotalTickets());
    }

    public List<Ticket> getAllTickets(){
        try{
            return ticketRepository.findAllTickets();
        }catch (IOException e) {
            throw new RepositoryException(MESSAGE_ERROR_REPOSITORY_FIND);
        }
    }
}

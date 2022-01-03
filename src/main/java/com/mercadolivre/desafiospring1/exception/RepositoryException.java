package com.mercadolivre.desafiospring1.exception;

public class RepositoryException extends RuntimeException{
    private static final long serialVersionUID = -7012775922197850149L;

    public RepositoryException(String message) {
        super(message);
    }

}
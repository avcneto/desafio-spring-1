package com.mercadolivre.desafiospring1.exception;

public class PurchaseException extends RuntimeException{
    private static final long serialVersionUID = -4993205001650484387L;

    public PurchaseException(String message) {
        super(message);
    }


}

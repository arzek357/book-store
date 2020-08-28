package com.geekbrains.book.store.exceptions;

public class BadSoapRequestException extends RuntimeException {
    public BadSoapRequestException(String message){
        super(message);
    }
}

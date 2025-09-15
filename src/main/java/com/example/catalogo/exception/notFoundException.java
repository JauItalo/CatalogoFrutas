package com.example.catalogo.exception;

public class notFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {super(message);}
}

package com.example.amazon.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String id) {
        super("could not found customer With id = " + id);
    }

}

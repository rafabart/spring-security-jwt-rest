package com.invillia.springsecurityjwtrest.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final Long id) {
        super("Usuário de ID: " + id + " não encontrado!");
    }
}

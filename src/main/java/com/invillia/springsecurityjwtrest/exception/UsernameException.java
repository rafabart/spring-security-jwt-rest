package com.invillia.springsecurityjwtrest.exception;

public class UsernameException extends RuntimeException {

    public UsernameException(final String username) {
        super("Username já utilizado, não pode ser repetido: " + username);
    }
}

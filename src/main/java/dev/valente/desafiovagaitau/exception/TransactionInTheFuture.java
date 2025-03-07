package dev.valente.desafiovagaitau.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransactionInTheFuture extends ResponseStatusException {
    public TransactionInTheFuture(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

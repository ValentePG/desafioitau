package dev.valente.desafiovagaitau.singleton;

import dev.valente.desafiovagaitau.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class Singletons {

    private static final Singletons INSTANCE = new Singletons();

    private final Queue<TransactionDTO> queue;

    private Singletons() {
        queue = new ConcurrentLinkedQueue<>();

    }

    public static Singletons getInstance() {
        return INSTANCE;
    }


    public Queue<TransactionDTO> getQueue() {
        return queue;
    }
}

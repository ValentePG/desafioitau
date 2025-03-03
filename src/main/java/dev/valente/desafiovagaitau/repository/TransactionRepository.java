package dev.valente.desafiovagaitau.repository;

import dev.valente.desafiovagaitau.domain.Transaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Queue;

@Getter
@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final Queue<Transaction> queue;

    public void clearAll(){
        queue.clear();
    }
}

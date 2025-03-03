package dev.valente.desafiovagaitau.service;

import dev.valente.desafiovagaitau.domain.Transaction;
import dev.valente.desafiovagaitau.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
@RequiredArgsConstructor
@Log4j2
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void saveTransaction(Transaction transaction) {
        var queue = transactionRepository.getQueue();
        queue.add(transaction);
        queue.forEach(q -> log.info("Queue: '{}'", q.getDataHora()));
    }

    public Queue<Transaction> getQueue() {
        return transactionRepository.getQueue();
    }

    public void clearAll(){
        transactionRepository.clearAll();
    }
}

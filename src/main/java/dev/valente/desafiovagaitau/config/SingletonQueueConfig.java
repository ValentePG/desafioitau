package dev.valente.desafiovagaitau.config;

import dev.valente.desafiovagaitau.domain.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
public class SingletonQueueConfig {

    @Bean
    public Queue<Transaction> queue() {
        return new ConcurrentLinkedQueue<>();
    }
}

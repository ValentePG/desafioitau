package dev.valente.desafiovagaitau.singleton;

import dev.valente.desafiovagaitau.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class Singletons {

    private static Singletons instance;
    private final ConcurrentHashMap<Long, DoubleSummaryStatistics> map;
    private final Queue<TransactionDTO> queue;

    private Singletons() {
        queue = new ConcurrentLinkedQueue<>();
        map = new ConcurrentHashMap<>();
        map.put(1L, new DoubleSummaryStatistics());
    }

    public static Singletons getInstance() {
        if(instance == null){
            return new Singletons();
        }
        return instance;
    }

    public ConcurrentHashMap<Long, DoubleSummaryStatistics> getMap() {
        return map;
    }
    public Queue<TransactionDTO> getQueue() { return queue; }
}

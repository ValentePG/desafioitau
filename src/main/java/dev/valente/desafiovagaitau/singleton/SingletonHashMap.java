package dev.valente.desafiovagaitau.singleton;

import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SingletonHashMap {

    private static SingletonHashMap instance;
    private final ConcurrentHashMap<Long, DoubleSummaryStatistics> map;

    private SingletonHashMap() {
        map = new ConcurrentHashMap<>();
        map.put(1L, new DoubleSummaryStatistics());
    }

    public static SingletonHashMap getInstance() {
        if(instance == null){
            return new SingletonHashMap();
        }
        return instance;
    }

    public ConcurrentHashMap<Long, DoubleSummaryStatistics> getMap() {
        return map;
    }
}

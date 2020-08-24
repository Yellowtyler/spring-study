package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.MethodStats;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.MethodStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodStatsService {
    @Autowired
    private MethodStatsRepository repository;

    public MethodStats getByMethodName(String name) {
        return repository.findByMethodName(name).orElseGet(() -> new MethodStats(name));
    }

    public MethodStats saveOrUpdate(MethodStats methodStat) {
        return repository.save(methodStat);
    }

    public List<MethodStats> findAll() {
        return repository.findAll();
    }
}

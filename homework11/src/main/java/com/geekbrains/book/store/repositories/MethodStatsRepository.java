package com.geekbrains.book.store.repositories;

import com.geekbrains.book.store.entities.MethodStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MethodStatsRepository extends JpaRepository<MethodStats, Long> {
    Optional<MethodStats> findByMethodName(String methodName);
}

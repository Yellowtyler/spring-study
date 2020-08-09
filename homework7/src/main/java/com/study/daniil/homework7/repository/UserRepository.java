package com.study.daniil.homework7.repository;

import com.study.daniil.homework7.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

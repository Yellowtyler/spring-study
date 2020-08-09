package com.study.daniil.homework7.service;


import com.study.daniil.homework7.exceptions.UserNotFoundException;
import com.study.daniil.homework7.model.User;
import com.study.daniil.homework7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) throws UserNotFoundException {
       return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("Пользователь с id " + id + " не найден!"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}

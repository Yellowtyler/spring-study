package com.study.daniil.service;

import com.study.daniil.exceptions.UserNotFoundException;
import com.study.daniil.model.User;
import com.study.daniil.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id);
        if(user == null) {
            throw new UserNotFoundException("Пользователь с id " + id + " не найден!");
        }
        return user;
    }

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}

package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository repository;
    private MqService mqService;

    public Order save(Order order) {
        return repository.save(order);
    }

    public void sendMessage(Order order){
        mqService.sendMessage(order.getId().toString());
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order with id: " + id + " not found"));
    }
}

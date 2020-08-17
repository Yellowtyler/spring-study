package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}

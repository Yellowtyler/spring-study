package com.geekbrains.book.store.controllers;


import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import com.geekbrains.book.store.repositories.OrderRepository;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    private UserService userService;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    @PostMapping
    public String addBook(@ModelAttribute Book book, @RequestParam Integer count) {
        OrderItem orderItem = new OrderItem(count, book.getPrice().intValue(), book.getId());
        cart.getOrderItems().add(orderItem);
        return "redirect:/books";
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("items", cart.getOrderItems());
        return "cart-page";
    }

    @PostMapping("/add")
    public String orderItem(@RequestParam(name = "count") Integer count, Principal principal, Cart cart) {
        User user = userService.findByUsername(principal.getName()).get();
        Order order = new Order(user);
        for (OrderItem item : cart.getOrderItems()) {
            item.setOrder(order);
            orderItemRepository.save(item);
        }
        orderRepository.save(order);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeFromCart(@ModelAttribute OrderItem orderItem) {
        cart.getOrderItems().remove(orderItem);
        return "redirect:/cart";
    }



}

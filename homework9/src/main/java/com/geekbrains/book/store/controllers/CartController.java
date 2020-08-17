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
import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    private UserService userService;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    @PostMapping
    public String addBook(@ModelAttribute Book book) {
        cart.getBooks().add(book);
        return "redirect:/books";
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("books", cart.getBooks());
        return "cart-page";
    }

    @PostMapping("/add")
    public String orderItem(@RequestParam(name = "count") Integer count, Principal principal, Cart cart) {
        User user = userService.findByUsername(principal.getName()).get();
        Order order = new Order(user);
        orderRepository.save(order);
        for (Book book : cart.getBooks()) {
            OrderItem orderItem = new OrderItem(
                    count, count*book.getPrice().intValue(),
                    book, order
            );
            orderItemRepository.save(orderItem);
        }
        cart.getBooks().clear();
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeFromCart(@ModelAttribute Book book) {
        cart.getBooks().remove(book);
        return "redirect:/cart";
    }

}

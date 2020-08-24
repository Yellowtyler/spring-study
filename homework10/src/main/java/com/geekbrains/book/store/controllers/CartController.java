package com.geekbrains.book.store.controllers;


import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.OrderItemService;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    private BookService bookService;
    private UserService userService;
    private OrderItemService orderItemService;
    private OrderService orderService;

    @PostMapping
    public String addBook(@ModelAttribute Book book, @RequestParam Integer count) {
        OrderItem orderItem = new OrderItem(count, book.getPrice().intValue(), book.getId());
        cart.getOrderItems().add(orderItem);
        return "redirect:/books";
    }

    @GetMapping
    public String getCart(Model model) {
        List<String> titles = new ArrayList<>();
        for (OrderItem item : cart.getOrderItems()) {
            titles.add(bookService.findById(item.getBookId()).getTitle());
        }
        model.addAttribute("titles", titles);
        model.addAttribute("items", cart.getOrderItems());
        return "cart-page";
    }

    @PostMapping("/add")
    public String orderItem(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        Order order = new Order(user);
        order = orderService.save(order);
        for (OrderItem item : cart.getOrderItems()) {
            item.setOrder(order);
            orderItemService.save(item);
        }
        System.out.println(orderItemService.findAll().toString());
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeFromCart(@ModelAttribute OrderItem orderItem) {
        cart.getOrderItems().remove(orderItem);
        return "redirect:/cart";
    }



}

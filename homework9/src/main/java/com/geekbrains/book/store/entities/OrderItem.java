package com.geekbrains.book.store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="count")
    private Integer count;
    @Column(name="price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Integer count, Integer price, Book book, Order order) {
        this.count = count;
        this.price = price;
        this.book = book;
        this.order = order;
    }
}

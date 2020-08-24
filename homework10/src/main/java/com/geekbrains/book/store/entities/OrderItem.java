package com.geekbrains.book.store.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="count")
    private Integer count;
    @Column(name="price")
    private Integer price;

    private Long bookId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Integer count, Integer price, Long bookId) {
        this.count = count;
        this.price = price;
        this.bookId = bookId;
    }
}

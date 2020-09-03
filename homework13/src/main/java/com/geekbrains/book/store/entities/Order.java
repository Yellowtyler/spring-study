package com.geekbrains.book.store.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private String status;

    public Order(List<OrderItem> orderItems, User user) {
        this.orderItems = orderItems;
        this.user = user;
    }

    public Order(User user) {
        this.user = user;
    }
}

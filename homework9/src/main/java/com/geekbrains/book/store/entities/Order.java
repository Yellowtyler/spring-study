package com.geekbrains.book.store.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name="id")
    private Long id;

//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(User user) {
        this.user = user;
    }
}

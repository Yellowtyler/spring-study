package com.geekbrains.book.store.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "method_stats")
@Data
@NoArgsConstructor
public class MethodStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "call_amount")
    private Long callAmount;

    public MethodStats(String methodName) {
        this.methodName = methodName;
        this.callAmount = 0L;
    }

    public void incrementCallAmount() {
        callAmount += 1;
    }
}

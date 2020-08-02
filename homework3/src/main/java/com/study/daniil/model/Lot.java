package com.study.daniil.model;

import javax.persistence.*;

@Entity
@Table(name="lots")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="bet")
    private Integer bet;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Version
    private long version;

    public Lot() {
    }

    public Lot(String name, Integer bet, User user) {
        this.name = name;
        this.bet = bet;
        this.user = user;
    }

    public long getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBet() {
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bet=" + bet +
                ", version=" + version +
                '}';
    }
}
